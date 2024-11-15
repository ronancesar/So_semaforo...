import java.util.concurrent.Semaphore;

class LeitoresEscritoresSemaforos {
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore escritor = new Semaphore(1);
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
                mutex.acquire();
                leitores++;
                if (leitores == 1) {
                    escritor.acquire();
                }
                mutex.release();

                System.out.println("Leitor " + id + " está lendo...");
                Thread.sleep(1000);

                mutex.acquire();
                leitores--;
                if (leitores == 0) {
                    escritor.release();
                }
                mutex.release();
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
                escritor.acquire();
                System.out.println("Escritor " + id + " está escrevendo...");
                Thread.sleep(1000);
                escritor.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
