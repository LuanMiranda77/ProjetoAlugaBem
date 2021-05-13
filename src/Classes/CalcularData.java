package Classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Execoes.DataInvalida;
import Execoes.EmailJaCadastrado;

/*Esta class considero a mais importante porque ela possue os metodos de calcular todas as datas do sistemas
 * nela conta com seguintes metodos :
 * 
 * calcularSoma();
 * neste metodo recebo como parametro de entrada um inteiro que sera somado a data atual,
 * que e recebida peço new date, depois que e recebida a data e descostruida pelo metodo split da String que tira as // da data
 * depois e feito um for pra converter a data em int depois cria um calendaria gregoriano com a data somando o dia 
 * que foi digitado no meu caso tive que colocar um mes trinta por que tava avançado 30 dias a frente
 * por final ele retorna um data em string no fomando dia mes e ano colocando no simples formanto
 * 
 *  calcularDiminuir()
 *  neste metedo recebe duas data e retorna a diferença de em dias com inteiro
 *  1-recebe como parametro 2 datas em string e depois desconstruida e transformado em inteiro
 *  depois desmenbra o dia, mes , ano das duas datas existem dois metodos complementares 
 *  pra ao ajudar no calculo da diferença em dias,
 *  depois e ele chama o metodo gteDia recebe o dias das duas datas de subtrai as datas,
 *  primeiro antes de subtrair ele compara pra saber se e do mesmo ano,
 *  depois os meses e com o metodo anoBi serve pra testa se ano digitadi e bisexto se for ele diminui um do dai 
 *  por final esse metodo retorna um inteiro que como diferença de dias pela as datas.
 *  
 *  calcularNiver()
 *  ele recebe como paranmetro um data em string, desconstroi ela e passa pra inteiro
 *  depois faz a comparaçãoes entre dia se forma maior que 31 ou meno que 0,
 *  comprar o mes se e maior que 12 ou menor que zero, 
 *  compara o  ano pra saber se ano e maior que 100 anos ou se usuario tem a idade igual
 *  ou maior que 18 anos de idade
 *  
 *  ValidarEmail()
 *  tem como objetivo validar se o email ja esta cadastrado no sistema e retorna uma excption avisando 
 *  que o email ja esta cadastrado, recebe como paramentro um data em String. 
 *  
 
 */

public class CalcularData {
	
	 
	
	public String calcularSoma(int dia) {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy"); 
		 Date hoje = new Date();
		 String h = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(hoje);
		 String []lista=h.split("/");
		 int[]data=new int[3];

			for(int cont=0; cont<3;cont++) {
				data[cont]=Integer.parseInt(lista[cont]);
			}
	     Calendar c = new GregorianCalendar(data[2],data[1],data[0]); 
	     c.add(Calendar.DAY_OF_MONTH, dia-30);
	     String d = sd.format(c.getTime());
     
     return d;
	}
	public int calcularDiminir(String dataInical, String dataFinal) {
		int[] data1 = new int[3];
        int[] data2 = new int[3];
        int dias = 0;
        
        //Desmembra dia, mes e ano das datas;
        data1[0] = Integer.parseInt(dataInical.substring(0, 2)); //dia;
        data1[1] = Integer.parseInt(dataInical.substring(3, 5)); //mes;
        data1[2] = Integer.parseInt(dataInical.substring(6, 10)); //ano;
        data2[0] = Integer.parseInt(dataInical.substring(0, 2)); //dia;
        data2[1] = Integer.parseInt(dataFinal.substring(3, 5)); //mes;
        data2[2] = Integer.parseInt(dataFinal.substring(6, 10)); //ano;
        int m1 = getDia(data1[1], data1[2]);
        int m2 = getDia(data2[1], data2[2]);
        if (data2[2] == data1[2]) { // Calculo para o mesmo ano;
            if (data1[1] == data2[1]) {
                dias = data2[0] - data1[0];
            } else {
                int diasMes1 = (m1 - data1[0]) + 1; 	// numero de dias do primeiro mes (do dia x até o dia 30);
                int diasMes2 = m2 - (m2 - data2[0]);	// numero de dias do ultimo mes (do dia 1 até o dia x);
                int interMeses = (data2[1] - data1[1]);	// quantidade de meses entre o primeiro e o ultimo mes;
                if (interMeses == 0) {
                    dias = diasMes1 + diasMes2;
                } else {
                    for (int i = 1; i < interMeses; i++) {
                        dias += getDia(data1[1] + i, data1[2]);
                    }
                    dias += (diasMes1 + diasMes2);
                }
            }
        } else {//Calculo para anos diferentes
            int mesesAno1 = 12 - data1[1];	    	//Numero de Meses ate o fim do ano (O Mes1 não está incluído);
            int diasMes1 = m1 - data1[0];		//Numero de dias do Mes1(inicial);
            for (int i = 0; i < mesesAno1; i++) {
                dias += getDia(data1[1] + i, data1[2]);
            }
            dias += diasMes1;
            int diasMes2 = m2 - (m2 - data2[0]);	    //Numero de dias do ultimo mes do periodo;
            int mesesAno2 = data2[1] - 1;	            //Numero de meses do mes 1 ate o mes x do novo ano;
            for (int i = 0; i < mesesAno2; i++) {
                dias += getDia(data2[1], data2[2]);
            }
            dias += diasMes2;
            if (data2[2] - data1[2] > 1) {
                dias += 365 * (data2[2] - data1[2] - 1);    // quantidade de anos entre o primeiro e o ultimo ano;
            }
        }
        return dias;
		
	}
public static int getDia(int dia, int ano) {
    //O ano valor do ano será usado para verificar se o ano é bisexto
    int[] mes = new int[12];
    mes[0] = 31;
    if (anoBi(ano)) {
        mes[1] = 29;
    } else {
        mes[1] = 28;
    }
    mes[2] = 31;
    mes[3] = 30;
    mes[4] = 31;
    mes[5] = 30;
    mes[6] = 31;
    mes[7] = 31;
    mes[8] = 30;
    mes[9] = 31;
    mes[10] = 30;
    mes[11] = 31;
    return mes[dia - 1];
}
public static boolean anoBi(int year) {
    boolean retorno;
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                retorno = true;
            } else {
                retorno = false;
            }
        } else {
            retorno = true;
        }
    } else {
        retorno = false;
    }
    return retorno;
}

public void  calcularNiver(String data) throws DataInvalida{
	
	String[] dataseparada = data.split("/");
	
	int dia =Integer.parseInt(dataseparada[0]);
	int mes =Integer.parseInt(dataseparada[1]);
	int ano =Integer.parseInt(dataseparada[2]);
	
	if(dia>31||dia<0) {
		throw new DataInvalida();
	}
	else if(mes>12 || mes<0) {
		throw new DataInvalida();
	}
	else if(ano<1920 ||ano<0 ||ano>2000) {
		throw new DataInvalida();
	}
}
public void validarEmail(String email) throws EmailJaCadastrado{
	Persistencia pers= new Persistencia();
	Central cdi=pers.recuperarCentral("dados.xml");
	
	for(Locador a:cdi.getListaLocador()) {
		if(a.getLogin().equals(email)) {
			throw new EmailJaCadastrado();
		}
		
	}
	
	
	
}

}
