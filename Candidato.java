public class Candidato {
    private Integer number;
    private String name;
    private Boolean pessoa;

    public Candidato(Integer number, String name, Boolean pessoa){
        this.setName(name);
        this.setNumber(number);
        this.setPessoa(pessoa);
    }

    private void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setPessoa(Boolean pessoa){
        this.pessoa = pessoa;
    }

    public Boolean getPessoa(){
        return pessoa;
    }
}
