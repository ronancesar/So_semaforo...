package troca_mensagens;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class LeitoresEscritoresTrocaMensagens {
    private static final BlockingQueue<String> fila = new LinkedBlockingQueue<>();

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
                fila.put("Leitor " + id + " está lendo...");
                System.out.println(fila.take());
                Thread.sleep(1000);
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
                fila.put("Escritor " + id + " está escrevendo...");
                System.out.println(fila.take());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
