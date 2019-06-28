package by.training.task4.services;

/**CandyBuilderFactory is used to obtain a specific parser.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CandyBuilderFactory {

    /**
     * Enum of parsers.
     */
    private enum TypeParser {
        /**
         * string representation of  sax parser.
        */
        SAX,

        /**
         * string representation of  STAX parser.
         */
        STAX,

        /**
         * string representation of  DOM parser.
         */
        DOM
    }

    /** Method used to obtain a specific parser.
     *
     * @param typeParser string representation of parser
     * @return specific parser
     */
    public AbstractCandiesBuilder createCandyBuilder(final String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CandyDOMBuilder();
            case STAX:
                return new CandySTAXBuilder();
            case SAX:
                return new CandySAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }


}
