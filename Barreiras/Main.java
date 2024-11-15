package Barreiras;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class LeitoresEscritoresBarreiras {
    private static final CyclicBarrier barreira = new CyclicBarrier(2);

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
                System.out.println("Leitor " + id + " esperando na barreira...");
                barreira.await();
                System.out.println("Leitor " + id + " está lendo...");
                Thread.sleep(1000);
            } catch (InterruptedException | BrokenBarrierException e) {
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
                System.out.println("Escritor " + id + " esperando na barreira...");
                barreira.await();
                System.out.println("Escritor " + id + " está escrevendo...");
                Thread.sleep(1000);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
