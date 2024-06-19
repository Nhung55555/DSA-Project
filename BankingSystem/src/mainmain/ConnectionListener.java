//package mainmain;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public interface ConnectionListener {
//    void onConnectionChange(boolean isConnected);
//
//    class ConnectionMonitor {
//
//        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        private final List<ConnectionListener> listeners = new ArrayList<>();
//
//        public void startMonitoring() {
//            final Runnable checker = new Runnable() {
//                @Override
//                public void run() {
//                    boolean isConnected = InternetChecker.isInternetAvailable();
//                    notifyListeners(isConnected);
//                }
//            };
//
//            scheduler.scheduleAtFixedRate(checker, 0, 1, TimeUnit.MINUTES); // Check every minute
//        }
//
//        public void stopMonitoring() {
//            scheduler.shutdown();
//        }
//
//        public void addListener(ConnectionListener listener) {
//            listeners.add(listener);
//        }
//
//        private void notifyListeners(boolean isConnected) {
//            for (ConnectionListener listener : listeners) {
//                listener.onConnectionChange(isConnected);
//            }
//        }
//    }
//
//    class InternetChecker {
//
//        public static boolean isInternetAvailable() {
//            try {
//                URL url = new URL("http://www.google.com");
//                HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
//                urlConnect.setConnectTimeout(5000); // Set timeout to 5 seconds
//                urlConnect.connect();
//
//                return urlConnect.getResponseCode() == 200;
//            } catch (IOException e) {
//                return false;
//            }
//        }
//    }
//}
package mainmain;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public interface ConnectionListener {
    void onConnectionChange(boolean isConnected);

    class ConnectionMonitor {

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        private final List<ConnectionListener> listeners = new ArrayList<>();
        private boolean hasNotified = false;

        public void startMonitoring() {
            final Runnable checker = new Runnable() {
                @Override
                public void run() {
                    boolean isConnected = InternetChecker.isInternetAvailable();
                    if (!hasNotified) {
                        notifyListeners(isConnected);
                        hasNotified = true; // Set the flag to true after the first notification
                    }
                }
            };
            scheduler.scheduleAtFixedRate(checker, 0, 1, TimeUnit.MINUTES); // Check every minute
        }
        public void stopMonitoring() {
            scheduler.shutdown();
        }
        public void addListener(ConnectionListener listener) {
            listeners.add(listener);
        }

        private void notifyListeners(boolean isConnected) {
            for (ConnectionListener listener : listeners) {
                listener.onConnectionChange(isConnected);
            }
        }
    }

    class InternetChecker {

        public static boolean isInternetAvailable() {
            try {
                URL url = new URL("http://www.google.com");
                HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
                urlConnect.setConnectTimeout(5000); // Set timeout to 5 seconds
                urlConnect.connect();

                return urlConnect.getResponseCode() == 200;
            } catch (IOException e) {
                return false;
            }
        }
    }
}

