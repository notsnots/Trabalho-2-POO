package dados;

public class Filme extends Video {

	private String diretor;

	private double duracao;

	public Filme(int codigo, String titulo, String diretor, double duracao){
		super(codigo, titulo);
		this.diretor = diretor;
		this.duracao = duracao;
	}

	@Override
	public String geraTexto() {
		StringBuilder texto = new StringBuilder();
		try {
			texto.append(super.getCodigo()).append("-");
			texto.append(super.getTitulo()).append("-");
			texto.append(this.diretor != null ? this.diretor : "Desconhecido").append("-");
			texto.append(this.duracao).append("-");
			texto.append(this.calculaCusto());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return texto.toString();
	}

	public String getDiretor() {
		return diretor;
	}

	public double getDuracao() {
		return duracao;
	}

	@Override
	public double calculaCusto(){
		return duracao * 0.30;
	}
}
