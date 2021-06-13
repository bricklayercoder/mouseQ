package com.mouse;

public enum DataSanity {
    OVER_SIZED(){
        @Override
        public String toString() {
            return "OVER_SIZED";
        }
    },

    INCOMPATIBLE_BREED_STATUS(){
        @Override
        public String toString() {
            return "INCOMPATIBLE_BREED_STATUS";
        }
    },

    INCOMPATIBLE_AND_OVER_SIZED(){
        @Override
        public String toString() {
            return "INCOMPATIBLE_AND_OVERSIZED";
        }
    }

}
