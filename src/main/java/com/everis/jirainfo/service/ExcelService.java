package com.everis.jirainfo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.jirainfo.entity.Etapa;
import com.everis.jirainfo.entity.Item;
import com.everis.jirainfo.entity.Subtask;
import com.everis.jirainfo.entity.UserStory;
import com.everis.jirainfo.repository.ItemRepository;

@Service
public class ExcelService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	private Map<String, Integer> mapaCabecalho = new HashMap<String, Integer>();
	private Map<String, UserStory> mapaRelUserStorySubtasks = new HashMap<String, UserStory>();

	private static final String HEADER_CHAVE = "Chave";
	private static final String HEADER_TIPO_ITEM = "Tipo de Item";
	private static final String HEADER_RESUMO = "Resumo";
	private static final String HEADER_RESPONSAVEL = "Responsável";
	private static final String HEADER_SITUACAO = "Situação";
	private static final String HEADER_SUBTASKS = "Sub-Tarefas";
	private static final String HEADER_PONTOS= "Estimated Story Points";

	private static final String HEADER_ETAPA_STATUS_INICIAL = "Initial status";
	private static final String HEADER_ETAPA_DATA_STATUS_INICIAL = "Initial status date";
	private static final String HEADER_ETAPA_TEMPO_STATUS_INICIAL = "Time in initial status (s)";
	private static final String HEADER_ETAPA_STATUS_FINAL = "Final status";
	private static final String HEADER_ETAPA_TEMPO_STATUS_FINAL = "Time in final status (s)";

	private static final String TIPO_ITEM_TECNICAL_TASK = "Technical task";
	private static final String TIPO_ITEM_USER_STORY = "User Story";
	
	public void lerArquivo(File arquivoExcel) throws IOException {
		Map<String, Item> mapaChaveUserStory = new HashMap<String, Item>();
		Map<String, Item> mapaChaveSubtask = new HashMap<String, Item>();
		
		try {
			FileInputStream fis = new FileInputStream(arquivoExcel);

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);

			// guarda no mapaMesclado os campos mesclados
			// this.obterCamposMesclados(sheet);

			// obtém campos de cabecalho das 2 primeiras linhas do arquivo
			Iterator<Row> linhaIterator = sheet.rowIterator();
			Row cabecalho1 = linhaIterator.next();
			this.obterCamposCabecalho(cabecalho1);

			Row cabecalho2 = linhaIterator.next();
			this.obterCamposCabecalho(cabecalho2);

			// percorre linhas com valores
			Item item = null;
			Row linha = null;
			while (linhaIterator.hasNext()) {
				linha = linhaIterator.next();

				// se for a ultima linha
				if (linha.getCell(mapaCabecalho.get(HEADER_CHAVE)) == null) {
					break;
				}

				String chave = linha.getCell(mapaCabecalho.get(HEADER_CHAVE)).getStringCellValue();
				String tipoItem = linha.getCell(mapaCabecalho.get(HEADER_TIPO_ITEM)).getStringCellValue();

				// se chave não é vazia
				if (!chave.isEmpty()) {
					if (tipoItem.equals(TIPO_ITEM_TECNICAL_TASK)) {
						Subtask subtask = new Subtask();
						this.preencherItem(subtask, linha);
						this.preencherSubtask(subtask);
						mapaChaveSubtask.put(chave, subtask);
						item = subtask;
					} else if (tipoItem.equals(TIPO_ITEM_USER_STORY)) {
						UserStory userStory = new UserStory();
						this.preencherItem(userStory, linha);
						this.verificarSubtasks(userStory, linha);
						mapaChaveUserStory.put(chave, userStory);
						item = userStory;
					} else {
						item = new Item();
						this.preencherItem(item, linha);
					}
				}

				// se existe etapa para este item
				if (!linha.getCell(mapaCabecalho.get(HEADER_ETAPA_STATUS_INICIAL)).getStringCellValue().isEmpty()) {
					Etapa etapa = this.preencherEtapa(linha);
					etapa.setItem(item);
					item.getListaEtapas().add(etapa);
				}
			}
			
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// persistencia
		this.persistirMapaItens(mapaChaveUserStory);
		this.persistirMapaItens(mapaChaveSubtask);
	}

	
	/**
	 * Persistir Item
	 * @param mapaItens
	 */
	public void persistirMapaItens(Map<String, Item> mapaItens) {
		Set<String> chaves = mapaItens.keySet();
		Iterator<String> chavesIterator = chaves.iterator();

		while(chavesIterator.hasNext()) {
			String chave = chavesIterator.next();
			Item item = mapaItens.get(chave);
			System.out.println("Persistindo " + item.getChave());
			itemRepository.save(item);			
		}
	}
	
	/**
	 * Preenche o MapCabecalho com os campos da linha
	 * 
	 * @param linhaCabecalho
	 */
	public void obterCamposCabecalho(Row linhaCabecalho) {
		Iterator<Cell> headerCellIterator = linhaCabecalho.cellIterator();
		for (int col = 0; headerCellIterator.hasNext(); col++) {
			Cell celula = headerCellIterator.next();
			String chave = celula.getStringCellValue();
			if (!chave.isEmpty()) {
				mapaCabecalho.put(chave, col);
			}
		}
	}

	/**
	 * Prencher campos de item
	 * 
	 * @param item
	 */
	public void preencherItem(Item item, Row linha) {
		item.setChave(linha.getCell(mapaCabecalho.get(HEADER_CHAVE)).getStringCellValue());
		item.setResumo(linha.getCell(mapaCabecalho.get(HEADER_RESUMO)).getStringCellValue());
		item.setResponsavel(linha.getCell(mapaCabecalho.get(HEADER_RESPONSAVEL)).getStringCellValue());
		item.setSituacao(linha.getCell(mapaCabecalho.get(HEADER_SITUACAO)).getStringCellValue());
		
		String pontos = linha.getCell(mapaCabecalho.get(HEADER_PONTOS)).getStringCellValue();
		 
		item.setPontos(pontos.isBlank() ? 0 : Integer.parseInt(pontos));

		List<Etapa> listaEtapas = new ArrayList<Etapa>();
		item.setListaEtapas(listaEtapas);
	}

	/**
	 * Preenche os campos de Etapa 
	 * @param linha
	 * @return etapa
	 */
	public Etapa preencherEtapa(Row linha) {
		Etapa etapa = new Etapa();
		etapa.setStatusInicial(linha.getCell(mapaCabecalho.get(HEADER_ETAPA_STATUS_INICIAL)).getStringCellValue());
		etapa.setTempoStatusInicial(linha.getCell(mapaCabecalho.get(HEADER_ETAPA_TEMPO_STATUS_INICIAL)).getNumericCellValue());
		etapa.setStatusFinal(linha.getCell(mapaCabecalho.get(HEADER_ETAPA_STATUS_FINAL)).getStringCellValue());
		etapa.setTempoStatusFinal(linha.getCell(mapaCabecalho.get(HEADER_ETAPA_TEMPO_STATUS_FINAL)).getNumericCellValue());
	
		Date dataStatusInicial = linha.getCell(mapaCabecalho.get(HEADER_ETAPA_DATA_STATUS_INICIAL)).getDateCellValue();
		
		Calendar dateTime = null;
		if (dataStatusInicial != null) {
			dateTime = Calendar.getInstance();
			dateTime.setTime(dataStatusInicial);
		}
		
		etapa.setDataStatusInicial(dateTime);		
		
		return etapa;
	}
	
	/**
	 * Mapear subtasks de user story
	 * @param userStory
	 * @param linha
	 */
	public void verificarSubtasks(UserStory userStory, Row linha) {
		String subtasks = linha.getCell(mapaCabecalho.get(HEADER_SUBTASKS)).getStringCellValue();
		
		if (!subtasks.isBlank()) {
			subtasks = subtasks.replaceAll(" ", "");
			String[] arraySubtasks = subtasks.split(",");

			for (String subtask : arraySubtasks) {
				mapaRelUserStorySubtasks.put(subtask, userStory);
			}
			userStory.setListaSubtask(new ArrayList<Subtask>());
		}
	}
	
	/**
	 * Completar relacionamento user story - subtasks
	 * @param subtask
	 */
	public void preencherSubtask(Subtask subtask) {
		UserStory userStory = mapaRelUserStorySubtasks.get(subtask.getChave());
		
		if (userStory != null) {
			subtask.setUserstory(userStory);
			userStory.getListaSubtask().add(subtask);
		}
	}
}
