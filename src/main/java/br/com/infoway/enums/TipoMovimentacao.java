package br.com.infoway.enums;

public enum TipoMovimentacao {

	SAQUE(1, "Saque"),
	DEPOSITO(2, "Depósito"),
	TRANSFERENCIA(3, "Transferência");
	
	private int codigo;
	private String descricao;
	
	private TipoMovimentacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoMovimentacao toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(TipoMovimentacao tipoMovimentacao : TipoMovimentacao.values()) {
			if(codigo.equals(tipoMovimentacao.getCodigo())) {
				return tipoMovimentacao;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}