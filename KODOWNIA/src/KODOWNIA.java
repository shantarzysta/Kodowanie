
public class KODOWNIA {



			public static void main(String[] args) {
				/*249547 -> (20,12) 876310
				 * g(x)=0000111001011	
				 * slownik:
				 * mgx - macierz z wielomianem generujacym
				 * rtmp - reszta z dzielenia wielomianow temporary*
				 * infWord - macierz ze wszystkimi słowami informacyjnymi
				 * 
				 */
				int zeros;
				int n = 20;
				int k = 12;
				int r, q, z;
				r = n - k;
				q = (n - k)/2;
				int macierzG[][] = new int[k][n];
				int mKontrolna[][] = new int [n][n-k];
				int infWord[][] = new int[12][4096];
				int mgx[] = new int [9];
				int reszta[] = new int [9];
				int rtmp;
				int hT[][] = new int [n][n-k];
				
				for (int i = 0; i < 4096 ; i++) {
					z = i;
					for (int j = 11; j >= 0 ; j--) {
						infWord[j][i] = z%2;
						z = z/2;
						if(z==0) {
							break;
						}
					}
				}
				for (int j = 0; j < 20; j++) {
					for (int i = 0; i < 12; i++) {
						macierzG[i][j] = 0;
					}
				}
				macierzG[0][0] = 1;
				macierzG[0][1] = 1;
				macierzG[0][3] = 1;
				macierzG[0][6] = 1;
				macierzG[0][7] = 1;
				macierzG[0][8] = 1;
				for (int j = 1; j < k; j++) {
					for (int i = 0; i < n; i++) {
						if (i!=19) {
							if (macierzG[j - 1][i + 1] == 1) {
								macierzG[j][i] = 1;
							}
						}
						else{
							if (macierzG[j-1][0] == 1) {
								macierzG[j][i] = 1;
							}
						}
					}
				}
				/*
				for (int i = 0; i < k; i++) {
					for (int j = 0; j < n; j++) {
						System.out.print(macierzG[i][j]);
					}
					System.out.println();
				}
				//*/
				for (int i = 0; i < mgx.length; i++) {
					mgx[i] = macierzG[0][i];
					reszta[i] = 0;
				}
				for (int j = n - 1; j >= 8; j--) {
					int divMe[] = new int[j + 1];
					divMe[0] = 1;
					for (int i = 1; i < divMe.length; i++) {
						divMe[i] = 0;
					}
					zeros = divBin(mgx, reszta, divMe);
					for (int i = 8; i >= zeros; i--) {
						reszta[i] = reszta[i - zeros];
					}
					for (int i = 0; i < zeros; i++) {
						reszta[i] = 0;
					}
					/*odwracanie tablicy
					for (int i = 0; i < 5; i++) {
						rtmp = reszta[i];
						reszta[i] = reszta[8 - i];
						reszta[8 - i] = rtmp;
					}
					//
					System.out.print("w tablicy reszta:");
					for (int i = 0; i < 9; i++) {
						System.out.print(reszta[i]);
					}
					System.out.println();
					//
					System.out.print("wiersz[" + j + "]");
					//*/
					for (int i = 0; i < 8; i++) {
						hT[j][i] = reszta[i];
						//System.out.print(hT[j][i]);
					}
					//System.out.println();
					
				}
				for (int j = 0; j < 8; j++) {
					for (int i = 0; i < 8; i++) {
						if(i == j) {
							hT[j][i] = 1;
						}
						else {
							hT[j][i] = 0;
						}
					}
				}
				for (int j = 19; j >= 0; j--) {
					for (int i = 0; i < 8; i++) {
						System.out.print(hT[j][i]);
					}
					System.out.println("[" + j + "]");
				}
			}
			

	static int divBin(int[] mgx, int[] reszta, int[] divMe) {
		int metmp[] = new int[9];
		int counter = 0;
		int maxsteps = divMe.length - 7;
		int zeros;
		
		//System.out.println(divMe.length);
		
		//odwracanie kolejnosci 
		if (divMe.length == 9) {
			for (int i = 0; i < 8; i++) {
				metmp[8 - i] = divMe[i];
			}
		}
		else {
			for (int i = 0; i < 9; i++) {
				metmp[i] = divMe[8 - i];
			}
		}
		while (maxsteps > counter) {	
			//odejmowanie w dzieleniu pisemnym
			for (int i = 0; i < mgx.length; i++) {
				if (mgx[i] == metmp[i]) {
					reszta[i] = 0;
				} else {
					reszta[i] = 1;
				}
			}
			//spisywanie 0 z gory jak przy dzieleniu pisemnym
			if(reszta[8] == 0 && reszta[7] == 0 && reszta[6] == 0 && reszta[5] == 0 && reszta[4] == 0 && reszta[3] == 0 && reszta[2] == 0 && reszta[1] == 0) {
				counter = counter + 8;
				if (counter < maxsteps) {
					reszta[8] = reszta[0];
					for (int i = 0; i < 8; i++) {
						reszta[i] = 0;
					} 
				}
			}
			else if(reszta[8] == 0 && reszta[7] == 0 && reszta[6] == 0 && reszta[5] == 0 && reszta[4] == 0 && reszta[3] == 0 && reszta[2] == 0) {
				counter = counter + 7;
				if (counter < maxsteps) {
					for (int i = 8; i >= 7; i--) {
						reszta[i] = reszta[i - 7];
					}
					for (int i = 0; i < 7; i++) {
						reszta[i] = 0;
					} 
				}
			}
			else if(reszta[8] == 0 && reszta[7] == 0 && reszta[6] == 0 && reszta[5] == 0 && reszta[4] == 0 && reszta[3] == 0) {
				counter = counter + 6;
				if (counter < maxsteps) {
					for (int i = 8; i >= 6; i--) {
						reszta[i] = reszta[i - 6];
					}
					for (int i = 0; i < 6; i++) {
						reszta[i] = 0;
					} 
				}
			}
			else if(reszta[8] == 0 && reszta[7] == 0 && reszta[6] == 0 && reszta[5] == 0 && reszta[4] == 0) {
				counter = counter + 5;
				if (counter < maxsteps) {
					for (int i = 8; i >= 5; i--) {
						reszta[i] = reszta[i - 5];
					}
					for (int i = 0; i < 5; i++) {
						reszta[i] = 0;
					} 
				}
			}
			else if(reszta[8] == 0 && reszta[7] == 0 && reszta[6] == 0 && reszta[5] == 0) {
				counter = counter + 4;
				if (counter < maxsteps) {
					for (int i = 8; i >= 4; i--) {
						reszta[i] = reszta[i - 4];
					}
					for (int i = 0; i < 4; i++) {
						reszta[i] = 0;
					} 
				}
			}
			else if(reszta[8] == 0 && reszta[7] == 0 && reszta[6] == 0) {
				counter = counter + 3;
				if (counter < maxsteps) {
					for (int i = 8; i >= 3; i--) {
						reszta[i] = reszta[i - 3];
					}
					for (int i = 0; i < 3; i++) {
						reszta[i] = 0;
					} 
				}
			}
			else if(reszta[8] == 0 && reszta[7] == 0) {
				counter = counter + 2;
				if (counter < maxsteps) {
					for (int i = 8; i >= 2; i--) {
						reszta[i] = reszta[i - 2];
					}
					for (int i = 0; i < 2; i++) {
						reszta[i] = 0;
					} 
				}
			}
			else if(reszta[8] == 0) {
				counter++;
				if (counter < maxsteps) {
					for (int i = 8; i >= 1; i--) {
						reszta[i] = reszta[i - 1];
					}
					reszta[0] = 0;
				}
			}
			else {
				System.out.println("Brak reszty");
				break;
			}
			for (int i = 0; i < metmp.length; i++) {
				metmp[i] = reszta[i];
			}
		}
		zeros = counter - maxsteps + 1;
		return zeros;
	}
}
