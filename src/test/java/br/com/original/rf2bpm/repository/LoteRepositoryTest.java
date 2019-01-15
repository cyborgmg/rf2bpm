package br.com.original.rf2bpm.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.original.rf2bpm.dto.LoteDto;
import br.com.original.rf2bpm.entity.Arquivo;
import br.com.original.rf2bpm.entity.Linha;
import br.com.original.rf2bpm.util.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:rf2bpm.properties")
@Configuration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoteRepositoryTest {
	
	@Value( "${system.files}" )
	private String FS;

	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Test
	public void readFileSystem() throws IOException {
		
		arquivoRepository.deleteAll();
		
		Path configFilePath = Paths.get(FS);

		Files.list(configFilePath).filter(s -> s.toString().endsWith(".xml"))
								  .map(Path::getFileName)
								  .sorted()
							      .forEach(file -> this.processFile(file) );

	}
	
	private void processFile(Path file) {
		
		try {
			
			LoteDto loteDto = ((LoteDto) Utils.xml2obj( FileUtils.readFileToString(new File( FS+file.toString() )) , LoteDto.class) );
			
			Arquivo arquivo = new Arquivo();
			arquivo.setArquivo(file.toString());
			arquivo.setOperacao(loteDto.getCabecalho().getOperacao());
			arquivo.setSistemaOrigem(loteDto.getCabecalho().getSistemaOrigem());
			arquivo.setTimeStamp(loteDto.getCabecalho().getTimestamp());
			
			loteDto.getItems().forEach(item->arquivo.getLinhas().add(new Linha(item.getPayload(), arquivo)));
			
			arquivoRepository.save(arquivo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void lerArquivos(){
		arquivoRepository.findAll().forEach(arquivo->{
			System.out.println(arquivo.toString());
			arquivo.getLinhas().forEach(linha->System.out.println(linha.toString()));
		});
	}
	
}
