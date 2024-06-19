//import db_objs.User;
//import guis.BankingAppGui;
//import guis.LoginGui;
//import guis.RegisterGui;
//
//import javax.swing.*;
//import java.math.BigDecimal;
//
//public class AppLauncher {
//    public static void main(String[] args) {
//
//        // use invokeLater to make updates to the GUI more thread-safe
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LoginGui().setVisible(true);
////                new RegisterGui().setVisible(true);
////                new BankingAppGui(
////                        new User(4, "username", "password", new BigDecimal("20.00"))
////                ).setVisible(true);
//            }
//        });
//
//    }
//}

import guis.LoginGui;
import mainmain.ConnectionListener;

import javax.swing.*;

public class AppLauncher implements ConnectionListener {

    public static void main(String[] args) {
        AppLauncher appLauncher = new AppLauncher();
        appLauncher.startApplication();
    }

    private void startApplication() {
        ConnectionMonitor monitor = new ConnectionMonitor();
        monitor.addListener(this);
        monitor.startMonitoring();
    }

    @Override
    public void onConnectionChange(boolean isConnected) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (isConnected) {
                    System.out.println("Internet connection is stable. Launching application...");
                    new LoginGui().setVisible(true);
//                    new RegisterGui().setVisible(true);
//                    new BankingAppGui(
//                            new User(4, "username", "password", new BigDecimal("20.00"))
//                    ).setVisible(true);
                } else {
                    System.out.println("No internet connection. Please check your connection.");
                    JOptionPane.showMessageDialog(null,
                            "No internet connection. Please check your connection.",
                            "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

