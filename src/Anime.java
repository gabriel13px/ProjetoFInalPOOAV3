public class Anime extends Titulo implements Classificar{


     @Override
    public int getClassificacao(){
        return (int) (Media()/2);
    }
}
