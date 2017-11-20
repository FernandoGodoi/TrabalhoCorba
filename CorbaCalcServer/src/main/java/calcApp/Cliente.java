/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcApp;

import java.util.Properties;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;

/**
 *
 * @author Claidson
 */
public class Cliente {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("org.omg.CORBA.ORBInitialHost", "10.151.34.132");
        prop.put("org.omg.CORBA.ORBInitialPort", "1050");
      

        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, prop);
  
        
        org.omg.CORBA.Object obj = null;
        org.omg.CosNaming.NamingContext naming = null;

        try {
            obj = orb.resolve_initial_references("NamingService");
            naming = org.omg.CosNaming.NamingContextHelper.narrow(obj);

        } catch (org.omg.CORBA.ORBPackage.InvalidName nome) {
            System.out.println("Pau ao buscar o service orb");
            System.exit(0);
        }

        org.omg.CosNaming.NameComponent[] nome = new org.omg.CosNaming.NameComponent[1];
        nome[0] = new org.omg.CosNaming.NameComponent();
        nome[0].id = "calculadora";
        nome[0].kind = "calc";
        try {
            obj = naming.resolve(nome);
        } catch (org.omg.CosNaming.NamingContextPackage.NotFound e) {
            System.out.println("Naming Sevice não licalizado");
            System.exit(0);
        } catch (CannotProceed ex) {
            System.out.println("cliente parou");
            System.exit(0);
        } catch (InvalidName ex) {
            System.out.println("nome inválido [cliente]");
            System.exit(0);
        }
        calc calc = calcHelper.narrow(obj);
        try {
            System.out.println("1+1 :" + calc.soma(1, 2));
            System.out.println("1-1 :" + calc.sub(1, 2));
            System.out.println("1*1 :" + calc.multi(1, 2));
            System.out.println("1/1 :" + calc.div(1, 2));
        } catch (org.omg.CORBA.SystemException e) {
            System.out.println("Erro ao calcular no cliente CORBA " + e.getMessage());

        }

    }
}
