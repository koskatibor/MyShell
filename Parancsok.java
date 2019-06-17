package shell;

import shell.exceptions.ShellException;
import shell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Parancsok extends Shell{

    List<Integer> kupacok = new ArrayList<>();
    boolean jatekos = true;
    boolean elso_jatek = true;

    public Parancsok()
    {
        Random r = new Random();

        kupacok.add(3);
        kupacok.add(5);
        kupacok.add(8);

        for (int i = 0; i < kupacok.size(); i++) {
            System.out.println("Az " + (i+1) + ". kupacban " + kupacok.get(i) + " darab golyó van.");
        }

        if (jatekos)
            System.out.println("Az \"A\" játékos következik.");
        else
            System.out.println("A \"B\" játékos következik.");

        addCommand(new Command("new") {
            @Override
            public boolean execute(String... strings) {
                jatekos = true;
                kupacok.clear();
                int kupacok_szama;
                if (strings.length!=1)
                    return false;
                try{
                    kupacok_szama = Integer.parseInt(strings[0]);
                } catch (Exception e)
                {
                    return false;
                }

                if (kupacok_szama<=0)
                    return false;

                for (int i = 0; i < kupacok_szama; i++) {
                    kupacok.add(r.nextInt(10));
                }

                for (int i = 0; i < kupacok.size(); i++) {
                    System.out.println("Az " + (i+1) + ". kupacban " + kupacok.get(i) + " darab golyó van.");
                }

                if (jatekos)
                    System.out.println("Az \"A\" játékos következik.");
                else
                    System.out.println("A \"B\" játékos következik.");
                return true;
            }
        });

        addCommand(new Command("print") {
            @Override
            public boolean execute(String... strings) {
                if (strings.length!=0)
                    return false;

                for (int i = 0; i < kupacok.size(); i++) {
                    System.out.println("Az " + (i+1) + ". kupacban " + kupacok.get(i) + " darab golyó van.");
                }

                boolean vanmeg = false;
                for (int item: kupacok) {
                    if (item!=0)
                        vanmeg=true;
                }
                if (vanmeg)
                {
                    if (jatekos)
                        System.out.println("A játék folytatódik... Következik: A");
                    else
                        System.out.println("A játék folytatódik... Következik: B");
                }
                else
                {
                    if (jatekos)
                    {
                        System.out.println("A játék véget ért. Győztes: A");
                    }
                    else
                        System.out.println("A játék véget ért. Győztes: B");
                }
                return true;
            }
        });

        addCommand(new Command("take") {
            @Override
            public boolean execute(String... strings) {
                if (strings.length!=2)
                    return false;

                boolean vanmeg = false;
                for (int item: kupacok) {
                    if (item!=0)
                        vanmeg=true;
                }
                if (!vanmeg)
                    return false;

                int kupac;
                int darab;

                try {
                    kupac = Integer.parseInt(strings[0])-1;
                    darab = Integer.parseInt(strings[1]);
                } catch (Exception e)
                {
                    return false;
                }

                if (kupac<0 || darab<=0)
                    return false;

                if (kupac<0 || kupac>kupacok.size()-1)
                    return false;
                if (kupacok.get(kupac)<darab)
                    return false;


                kupacok.set(kupac, kupacok.get(kupac) - darab);

                for (int i = 0; i < kupacok.size(); i++) {
                    System.out.println("Az " + (i+1) + ". kupacban " + kupacok.get(i) + " darab golyó van.");
                }

                vanmeg = false;
                for (int item: kupacok) {
                    if (item!=0)
                        vanmeg=true;
                }
                if (vanmeg)
                {
                    if (jatekos)
                        System.out.println("A játék folytatódik... Következik: B");
                    else
                        System.out.println("A játék folytatódik... Következik: A");
                }
                else
                {
                    if (jatekos)
                    {
                        System.out.println("A játék véget ért. Győztes: B");
                    }
                    else
                        System.out.println("A játék véget ért. Győztes: A");
                }

                if (jatekos)
                    jatekos=false;
                else
                    jatekos=true;

                return true;
            }
        });

    }

}
