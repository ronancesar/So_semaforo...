package monitores;

class LeitoresEscritoresMonitores {
    
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
        public synchronized void run() {
            try {
                leitores++;
                System.out.println("Leitor " + id + " está lendo...");
                Thread.sleep(1000);
                leitores--;
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
        public synchronized void run() {
            try {
                System.out.println("Escritor " + id + " está escrevendo...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

