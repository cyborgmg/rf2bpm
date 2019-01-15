package br.com.original.rf2bpm.common;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessAll {
	
	@EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
	public void start() {
		
		System.out.println(".................executou=");
		
	}

}
