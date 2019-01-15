package br.com.original.rf2bpm.files;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.original.rf2bpm.dto.CabecalhoDto;
import br.com.original.rf2bpm.dto.ItemDto;
import br.com.original.rf2bpm.dto.LoteDto;
import br.com.original.rf2bpm.util.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:rf2bpm.properties")
@Configuration
public class FilesTest {
	
	@Value( "${system.files}" )
	private String FS;
	
	@Before
	public void init() throws IOException{
		FileUtils.cleanDirectory(new File(FS));
	}
	
	@Test
	public void produceArquivos() throws IOException, JAXBException{

		for (int i = 0; i < 10; i++) {
			
			
			LoteDto loteDto = new LoteDto();

			CabecalhoDto cabecalhoDto = new CabecalhoDto();
			cabecalhoDto.setOperacao("operacao");
			cabecalhoDto.setSistemaOrigem("sistemaOrigem");
			cabecalhoDto.setTimestamp("timestamp");
			
			loteDto.setCabecalho(cabecalhoDto);
			
			StringBuilder sb = new StringBuilder("<![CDATA[");
			sb.append("<inicioMigracaoContaPagamento>");
			sb.append("<cpf>89298409912</cpf>");
			sb.append("<codigoPessoa>800823122</codigoPessoa>");
			sb.append("<numeroConta>6385793</numeroConta>");
			sb.append("<solicitanteMigracao>CRGR</solicitanteMigracao>");
			sb.append("<motivoSolicitacaoMigracao>AUMENTO_LIMITE</motivoSolicitacaoMigracao>");
			sb.append("<idCallback>HSJD73894939</idCallback>");
			sb.append("<serviceIdCallback>processAccountMigrationCredit</serviceIdCallback>");
			sb.append("</inicioMigracaoContaPagamento>");
			sb.append("]]>");
			
			loteDto.getItems().add(new ItemDto(sb.toString()));
			loteDto.getItems().add(new ItemDto(sb.toString()));
			loteDto.getItems().add(new ItemDto(sb.toString()));

			FileUtils.writeStringToFile(new File( FS + "Arquivo" + i + ".xml"), Utils.obj2xml(loteDto).replaceAll("&lt;", "<").replaceAll("&gt;", ">"));

		
		
		}
	}
	


}
