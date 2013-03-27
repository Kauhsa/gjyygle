
package Bibtex;

public enum BibtexField {
    ADDRESS {
        @Override
        public String getName() {
            return "Address";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    AUTHOR {
        @Override
        public String getName() {
            return "Author";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    TITLE {
        @Override
        public String getName() {
            return "Title";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    JOURNAL {
        @Override
        public String getName() {
            return "Journal";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    YEAR {
        @Override
        public String getName() {
            return "Year";
        }
        @Override
        public boolean validate(String input) {
            return validateInteger(input);
        }
    },
    VOLUME {
        @Override
        public String getName() {
            return "Volume";
        }
        @Override
        public boolean validate(String input) {
            return validateInteger(input);
        }
    },
    NUMBER {
        @Override
        public String getName() {
            return "Number";
        }
        @Override
        public boolean validate(String input) {
            return validateInteger(input);
        }
    },
    PAGESSTART {
        @Override
        public String getName() {
            return "PagesStart";
        }
        @Override
        public boolean validate(String input) {
            return validateInteger(input);
        }
    },
    PAGESEND {
        @Override
        public String getName() {
            return "PagesEnd";
        }
        @Override
        public boolean validate(String input) {
            return validateInteger(input);
        }
    },
    MONTH {
        @Override
        public String getName() {
            return "Month";
        }
        @Override
        public boolean validate(String input) {
            return validateInteger(input);
        }
    },
    NOTE {
        @Override
        public String getName() {
            return "Note";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    KEY {
        @Override
        public String getName() {
            return "Key";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    };
    abstract public String getName();
    abstract public boolean validate(String input);
    public boolean validateInteger(String input) {
        try {
            Integer.decode(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
