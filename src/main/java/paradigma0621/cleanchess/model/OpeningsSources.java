package paradigma0621.cleanchess.model;

public class OpeningsSources {
	public static String getOpeningPath(String numVariant) {
		String stringPGNname;

		if (numVariant.equals("001")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/DefesaDoisCavalos/DefesaDoisCavalos.Var.01-Ataque_Fegatello.A.pgn";
			System.out.println("001 de variante: DefesaDoisCavalos/DefesaDoisCavalos.Var.01-Ataque_Fegatello.A");
			return stringPGNname;
		}

		if (numVariant.equals("002")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/GiuocoPiano/01-GiuocoPiano-4....Cf6.pgn";
			System.out.println("002 de variante: GiuocoPiano/01-GiuocoPiano-4....Cf6");
			return stringPGNname;
		}

		if (numVariant.equals("003")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/GiuocoPiano/02-GioucoPiano-4...Cf6....7.Cc3.pgn";
			System.out.println("003 de variante: GiuocoPiano/02-GioucoPiano-4...Cf6....7.Cc3");
			return stringPGNname;
		}

		if (numVariant.equals("004")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/GiuocoPiano/03-GioucoPiano-4....De7.pgn";
			System.out.println("004 de variante: GiuocoPiano/03-GioucoPiano-4....De7");
			return stringPGNname;
		}

		if (numVariant.equals("005")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/GiuocoPiano/04-GioucoPiano-4.d3.pgn";
			System.out.println("005 de variante: GiuocoPiano/04-GioucoPiano-4.d3");
			return stringPGNname;
		}

		if (numVariant.equals("006")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/GiuocoPiano/MCAXpg44_jogarEstaComAsPretas.pgn";
			System.out.println("006 de variante: GiuocoPiano/MCAXpg44_jogarEstaComAsPretas");
			return stringPGNname;
		}


		/*
		if (numVariant.equals("001")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Dama/01_VarianteDasTrocas_Variante01.pgn";
			System.out.println("001 de variante: VarianteDasTrocas_Variante01.pgn");
			return stringPGNname;
			}
		
		if (numVariant.equals("002")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Dama/02_VarianteDasTrocas_Variante02.pgn";
			System.out.println("002 de variante: VarianteDasTrocas_Variante02.pgn");
			return stringPGNname; 
			}

		if (numVariant.equals("003")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Dama/03_VarianteDasTrocas_Variante03.pgn";
			System.out.println("003 de variante: VarianteDasTrocas_Variante03.pgn");
			return stringPGNname; 
			}					
		
		if (numVariant.equals("004")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Dama/DefesaEslava_CAB_e_CAP/01_DefesaEslava_VarianteMaisImportante (ExtraidoDoPGNgeral-aquiApenasAvariantePrincipalDoXadrezBasico).pgn";
			System.out.println("004_DefesaEslava_VarianteMaisImportante (ExtraidoDoPGNgeral-aquiApenasAvariantePrincipalDoXadrezBasico)");
			return stringPGNname; 
			}					

		if (numVariant.equals("005")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Dama/DefesaEslava_CAB_e_CAP/01_DefesaEslava_VarianteMaisImportante(a)_FonteXB.pgn";
			System.out.println("005: 01_DefesaEslava_VarianteMaisImportante(a)_FonteXB");
			return stringPGNname; 
			}					

		if (numVariant.equals("006")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Dama/DefesaEslava_CAB_e_CAP/01_DefesaEslava_VarianteMaisImportante(b)_FonteXB.pgn";
			System.out.println("006 de variante: 01_DefesaEslava_VarianteMaisImportante(b)_FonteXB");
			return stringPGNname; 
			}					

		if (numVariant.equals("007")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Dama/DefesaEslava_CAB_e_CAP/03_DefesaEslava_VarianteDaTroca.pgn";
			System.out.println("007 de variante: 03_DefesaEslava_VarianteDaTroca.pgn");
			return stringPGNname; 
			}					
		*/
		
		
		
//----------------------------DEF FRANCESA
		if (numVariant.equals("100")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/java/Memorize.IntermidiareBuild/resources-aberturas.pgn/DefesaFrancesa/DefesaFrancesa_A.01.pgn";
			System.out.println("001 de variante: DefesaFrancesa_A.pgn");
			return stringPGNname; 
			}
		
		if (numVariant.equals("101")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/java/Memorize.IntermidiareBuild/resources-aberturas.pgn/DefesaFrancesa/DefesaFrancesa_B.01.pgn";
			System.out.println("002 de variante: DefesaFrancesa_B.pgn");
			return stringPGNname; 
			}

		if (numVariant.equals("102")) {
			stringPGNname = "/home/lucas/Documentos/xadrez/java/Memorize.IntermidiareBuild/resources-aberturas.pgn/DefesaFrancesa/DefesaFrancesa_D.01.pgn";
			System.out.println("002 de variante: DefesaFrancesa_D.pgn");
			return stringPGNname; 
			}	
//--------------------------
		System.out.println("Not found none Opening with number: " + numVariant);
	return null;
	}
}
