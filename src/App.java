import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /*
        reconstruire grille sudoku 
        charger a partir d'un fichier .txt
        manipuler la grille (placements verifications) ?????
        reproduire la grille a l'ecran ou dans un autre fichier
        triplet:
        x ligne 
        y colonne
        z valeur 
        

        partie 2 erronee et sans retour de chariot


        1. charger  un fichier (online ou sur disque ou stdin) ** gerer les erreurs de lecture
        2. valider si la grille Sudoku est valable (9x9 / 1 fois par rangee et 1 fois par colonne) **generer une exception
        3. transposition au tableau correspondant à un calcul de matrice transposée et afficher le résultat à l’écran

        tester avec plusieurs grilles inclure des grilles asymetrique
        Donner le resultat de 2 tests differents (screenshot ok pour le resultats de ces tests)

        expliquer tout (fn var et qualifiants)

        */
        
        File FichierALire = new File("partie1.txt");
        try (
            FileReader unFichier = new FileReader(FichierALire);
            BufferedReader leBuffer = new BufferedReader(unFichier);
        ) {
            // Nous avons les fichiers partie1.txt,
            // partie2.txt et partie3.txt.
            System.out.println("ligne: "+ leBuffer.readLine());
        } catch (FileNotFoundException exception) {
            System.out.println(" Fichier introuvable!");
        }
        
        
        File fichier = new File ("sudoku.txt");
		Scanner sc = new Scanner(fichier);
		int [][] grille = new int [9][9];
		
		while (sc.hasNext())
		{
			//read sudoku from text file into 9x9 array
			for (int i = 0; i < 9; i ++)
			{
				for (int j = 0; j < 9; j++)
				{
                    grille[i][j] = sc.nextInt();
					//output the array
					System.out.print (grille[i][j] + " ");
				}
				//add new line at the end of each row
				System.out.println();
			}
			
		}
		//output whether it is valid or invalid
		if (!isValid(grille))
		{
			System.out.println ("\nNot valid Sudoku grid.");
		}
		else
		{
			System.out.println ("\nSudoku grid is valid. GREAT JOB!!!");
		}
        sc.close();
    }
    public static boolean isValid (int arr[][])
    {
        //check if rows contain valid numbers 1-9
        for (int i = 0; i < 9; i++)
        {
            if (!portionValid(arr[i]))
            {
                return false;
            }
        }
        //check if columns contain valid numbers 1-9
        for (int i = 0; i < 9; i++)
        {
            //create a single dimensional column array to be passed to the checking method
            int[] column = new int[9];
            for (int j = 0; j < 9; j++)
            {
                column[j] = arr[j][i];
            }
            if (!portionValid(column))
            {
                return false;
            }
        }
        //check if 3x3 box has valid numbers 1-9
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int k = 0;
                int [] list = new int [9];
                //make the numbers in 3x3 box into a single dimensional array
                for (int row = i * 3; row < i * 3 + 3; row++)
                {
                    for (int column = j * 3; column < i * 3 + 3; column++)
                    {
                        list[k++] = arr[row][column];
                    }
                }
                if (!portionValid (list))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean portionValid (int checkArr [])
    {
        //method to check whether a array list of 9 integers has numbers 1-9
        int[]temp = new int[checkArr.length];
        System.arraycopy(checkArr, 0, temp, 0, checkArr.length);
        //sory the copied array
        java.util.Arrays.sort(temp);
        //check that the sorted numbers are in sequential order 1+ for each
        for (int i = 0; i < 9; i++)
        {
            if (temp[i] != i + 1)
            {
                return false;
            }
        }
        return true;
    }
}
