package com.cagezz;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

public enum Status {
    MATING{
        @Override
        public String toString(){
           return "MATING";
      }
    },
    WEANING{
        @Override
        public String toString(){
            return "WEANING";
        }
    },
    MAINTAINING{
       @Override
       public String toString(){
           return "MAINTAINING";
       }
    },

    GESTATING{
        @Override
        public String toString(){
            return "GESTATING";
        }
    },

    NURSING {
        @Override
        public String toString() {
            return "FOSTERING";
        }
    },

    SACRIFICED{
        @Override
        public String toString() {
            return "SACRIFICED";
        }
    },

    EMPTY{
        @Override
        public String toString() {
            return "EMPTY";
        }
    },



}
