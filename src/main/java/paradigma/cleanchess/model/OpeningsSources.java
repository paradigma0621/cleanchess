package paradigma.cleanchess.model;

public class OpeningsSources {

	public static String stringPGNname;

	private OpeningsSources() {	}

	public static String getOpeningPath(String numVariant) {
		if (numVariant.equals("001")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_1.A__Var.Morphy_Var.Fechada_jogarTantoComBRancasComoComPretas.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("002")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_2.B_Steinitz_Moderna.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("003")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_3.Var_C.1.0-Var.Aberta.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("004")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_3.Var_C.1.1-Var.Aberta.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("005")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_3.Var_C.2.0-Var.Aberta.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("006")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_4.VarD.Berlinense.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("007")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_5.VarE.Defesa_Steinitz__VAriante_do_MCAX-poremAdoXadrezBasicoEhMelhor(pg327).pgn";
			return stringPGNname;
		}

		if (numVariant.equals("008")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_5.VarE.Defesa_Steinitz__VAriante_do_MCAX_poremAdoXadrezBasicoEhMelhor_pg327.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("009")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_6.VarF.VarianteClássica.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("010")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_7.Var_G.DefesaBird.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("011")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_8.Var_H.DefesaSchliemann.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("012")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_9.Var_I_AtaqueWorrall.pgn";
			return stringPGNname;
		}

		if (numVariant.equals("013")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/AnotacoesPessoais/aberturas/Peao_Rei/01_RuyLopes/ruyLopes_9III-Worrall.pgn";
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
			stringPGNname = "file:/home/lucas/Documentos/xadrez/java/Memorize.IntermidiareBuild/resources-aberturas.pgn/DefesaFrancesa/DefesaFrancesa_A.01.pgn";
			System.out.println("001 de variante: DefesaFrancesa_A.pgn");
			return stringPGNname; 
		}
		
		if (numVariant.equals("101")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/java/Memorize.IntermidiareBuild/resources-aberturas.pgn/DefesaFrancesa/DefesaFrancesa_B.01.pgn";
			System.out.println("002 de variante: DefesaFrancesa_B.pgn");
			return stringPGNname; 
		}

		if (numVariant.equals("102")) {
			stringPGNname = "file:/home/lucas/Documentos/xadrez/java/Memorize.IntermidiareBuild/resources-aberturas.pgn/DefesaFrancesa/DefesaFrancesa_D.01.pgn";
			System.out.println("002 de variante: DefesaFrancesa_D.pgn");
			return stringPGNname; 
		}
//--------------------------
		System.out.println("Not found none Opening with number: " + numVariant);
		return null;
	}
}
