
package gjyygle.bibtex;

import java.util.ArrayList;
import java.util.List;

public enum BibtexEntryType {
    ARTICLE {
        @Override
        public String getName() {
            return "Article";
        }
        @Override
        public BibtexField[] getRequiredFields() {
            BibtexField[] ret = { BibtexField.AUTHOR,
                                  BibtexField.TITLE,
                                  BibtexField.JOURNAL,
                                  BibtexField.YEAR,
                                  BibtexField.ID
            };
            return ret;
        }
        @Override
        public BibtexField[] getOptionalFields() {
            BibtexField[] ret = { BibtexField.VOLUME,
                                  BibtexField.NUMBER,
                                  BibtexField.PAGES,
                                  BibtexField.MONTH,
                                  BibtexField.NOTE,
                                  BibtexField.KEY
            };
            return ret;
        }
    },
    BOOK {
        @Override
        public String getName() {
            return "Book";
        }
        @Override
        public BibtexField[] getRequiredFields() {
            BibtexField[] ret = { BibtexField.AUTHOR,
                                  BibtexField.EDITOR,
                                  BibtexField.TITLE,
                                  BibtexField.PUBLISHER,
                                  BibtexField.YEAR,
            };
            return ret;
        }
        @Override
        public BibtexField[] getOptionalFields() {
            BibtexField[] ret = { BibtexField.VOLUME,
                                  BibtexField.NUMBER,
                                  BibtexField.SERIES,
                                  BibtexField.ADDRESS,
                                  BibtexField.EDITION,
                                  BibtexField.MONTH,
                                  BibtexField.NOTE,
                                  BibtexField.KEY,
            };
            return ret;
        }
    };
    abstract public String getName();
    abstract public BibtexField[] getRequiredFields();
    abstract public BibtexField[] getOptionalFields();
}
