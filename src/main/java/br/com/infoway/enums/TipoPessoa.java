package br.com.infoway.enums;

public enum TipoPessoa {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int codigo;
	private String descricao;
	
	private TipoPessoa(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPessoa toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(TipoPessoa tipoPessoa : TipoPessoa.values()) {
			if(codigo.equals(tipoPessoa.getCodigo())) {
				return tipoPessoa;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}