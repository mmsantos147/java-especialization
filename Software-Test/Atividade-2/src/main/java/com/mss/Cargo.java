package com.mss;

public enum Cargo {
    DESENVOLVEDOR {
        @Override
        public double calcularDesconto(double salarioBase) {
            return salarioBase >= 3000.00 ? salarioBase * 0.20 : salarioBase * 0.10; 
        }
    },
    
    DBA {
        @Override
        public double calcularDesconto(double salarioBase) {
            return salarioBase >= 2000.00 ? salarioBase * 0.25 : salarioBase * 0.15;
        }
    },

    TESTADOR {
        @Override
        public double calcularDesconto(double salarioBase) {
            return salarioBase >= 2000.00 ? salarioBase * 0.25 : salarioBase * 0.15;
        }
    },

    GERENTE {
        @Override
        public double calcularDesconto(double salarioBase) {
            return salarioBase >= 5000.00 ? salarioBase * 0.30 : salarioBase * 0.20; 
        }
    };

    public abstract double calcularDesconto(double salarioBase);
}
