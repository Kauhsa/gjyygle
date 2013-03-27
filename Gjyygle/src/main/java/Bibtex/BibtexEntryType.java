
package Bibtex;

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
            };
            return ret;
        }
        @Override
        public BibtexField[] getOptionalFields() {
            BibtexField[] ret = { BibtexField.VOLUME,
                                  BibtexField.NUMBER,
                                  BibtexField.PAGESSTART,
                                  BibtexField.PAGESEND,
                                  BibtexField.MONTH,
                                  BibtexField.NOTE,
                                  BibtexField.KEY
            };
            return ret;
        }
    };
    abstract public String getName();
    abstract public BibtexField[] getRequiredFields();
    abstract public BibtexField[] getOptionalFields();
}
