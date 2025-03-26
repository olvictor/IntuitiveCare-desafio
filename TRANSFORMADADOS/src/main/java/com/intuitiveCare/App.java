package com.intuitiveCare;
import com.intuitiveCare.service.TransformaDados;
public class App 
{
    public static void main( String[] args )
    {
        try{
            TransformaDados.main(args);
            System.out.println("Processo de extração concluído com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao executar o processo: " + e.getMessage());
        }

    }
}
