package org.transparencia.gov2go.model.constantes;

import java.util.HashMap;
import java.util.Map;

public class ExtensoesArquivo {
	
	public static Map<String, String> EXTENSOES;
	
	static {
		EXTENSOES = new HashMap<>();
		EXTENSOES.put("image/jpg", ".jpg");
		EXTENSOES.put("image/jpeg", ".jpeg");
	}
	
}
