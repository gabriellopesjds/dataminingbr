package tcc.gabriel.datamining.publicacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import tcc.gabriel.datamining.sentiment.SenticNet;

public class SuporteExcel {

	private static final String fileNamePortugues = "src/main/resources/senticnet/dataset_portugues.xls";
	private static final String fileNameIngles = "src/main/resources/senticnet/dataset_english.xls";
	private static final String fileNameAnalisado = "src/main/resources/senticnet/dataset_analisado.xls";
	private String fileName;
	private String linguaguem;
	private List<Publicacao> listaDePublicacao = new ArrayList<>();
	
	public SuporteExcel(String linguaguem) {
		this.linguaguem = linguaguem;
		if(linguaguem.equals("pt")) {
			fileName = fileNamePortugues;
		}else {
			fileName = fileNameIngles;
		}
	}

	public void abrirExcel() throws IOException {

		try {
			FileInputStream arquivo = new FileInputStream(new File(fileName));
			HSSFWorkbook workbook = new HSSFWorkbook(arquivo);
			HSSFSheet sheetPublicacao = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheetPublicacao.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				Publicacao publicacao = new Publicacao();
				listaDePublicacao.add(publicacao);
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						publicacao.setMensagem(cell.getStringCellValue());
						break;
					case 1:
						publicacao.setPolaridade(cell.getNumericCellValue());
						break;
					}
				}
			}
			arquivo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void criarExcel() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetPublicacao = workbook.createSheet("Publicacao");
         
        int rownum = 0;
        for (Publicacao publicacao : listaDePublicacao) {
            Row row = sheetPublicacao.createRow(rownum++);
            int cellnum = 0;
            Cell cellMensagem = row.createCell(cellnum++);
            cellMensagem.setCellValue(publicacao.getMensagem());
            Cell cellPolaridade = row.createCell(cellnum++);
            cellPolaridade.setCellValue(publicacao.getPolaridade());
            Cell cellPolaridadeAnalisada = row.createCell(cellnum++);
            cellPolaridadeAnalisada.setCellValue(publicacao.getPolaridadeAnalisada());
            Cell cellComparacao = row.createCell(cellnum++);
            cellComparacao.setCellValue(publicacao.getPolaridade().equals(publicacao.getPolaridadeAnalisada()));
            
        }
         
        try {
            FileOutputStream out = 
                    new FileOutputStream(new File(fileNameAnalisado));
            workbook.write(out);
            out.close();
            
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
               System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
               System.out.println("Erro na edição do arquivo!");
        }
	}

	public List<Publicacao> getListaDePublicacao() {
		return listaDePublicacao;
	}

	public List<Publicacao> analisarTexto() {
		SenticNet senticnet = new SenticNet();
		for (Publicacao publicacao : listaDePublicacao) {
			publicacao.setPolaridadeAnalisada(senticnet.analisarTexto(publicacao.getMensagem(),linguaguem));
		}
		return listaDePublicacao;
	}

}
