
package gjyygle.bibtex;

public enum BibtexField {
    ID {
        @Override
        public String getName() {
            return "ID";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
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
    PAGES {
        @Override
        public String getName() {
            return "Pages";
        }
        @Override
        public boolean validate(String input) {
            return true;
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
    },
    EDITOR {
        @Override
        public String getName() {
            return "Editor";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    PUBLISHER {
        @Override
        public String getName() {
            return "Publisher";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    SERIES {
        @Override
        public String getName() {
            return "Series";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    EDITION {
        @Override
        public String getName() {
            return "Edition";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    BOOKTITLE {
        @Override
        public String getName() {
            return "Booktitle";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    },
    ORGANIZATION {
        @Override
        public String getName() {
            return "Organization";
        }
        @Override
        public boolean validate(String input) {
            return true;
        }
    };
    abstract public String getName();
    abstract public boolean validate(String input);
    @Override
    public String toString() {
        return getName();
    }
    public boolean validateInteger(String input) {
        if (input.equals(""))
        {
            return true;
        }
        try {
            Integer.decode(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
