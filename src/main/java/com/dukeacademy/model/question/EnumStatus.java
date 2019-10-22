package com.dukeacademy.model.question;

public enum EnumStatus {
    NEW {
        public String toString() {
            return "New";
        }
    },
    ATTEMPTED {
        public String toString() {
            return "Attempted";
        }
    },
    PASSED {
        public String toString() {
            return "Passed";
        }
    }
}
