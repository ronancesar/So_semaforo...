package mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LeitoresEscritoresMutex {
    private static final Lock mutex = new ReentrantLock();
   
    private static int leitores = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Leitor(i)).start();
            new Thread(new Escritor(i)).start();
        }
    }

    static class Leitor implements Runnable {
        private final int id;

        Leitor(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                mutex.lock();
                leitores++;
                mutex.unlock();

                System.out.println("Leitor " + id + " está lendo...");
                Thread.sleep(1000);

                mutex.lock();
                leitores--;
                mutex.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Escritor implements Runnable {
        private final int id;

        Escritor(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                mutex.lock();
                System.out.println("Escritor " + id + " está escrevendo...");
                Thread.sleep(1000);
                mutex.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
