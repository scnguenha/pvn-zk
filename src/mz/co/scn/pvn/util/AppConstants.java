package mz.co.scn.pvn.util;

/**
 * Created by Sidónio Goenha on 11/29/2016.
 */
public class AppConstants {
	public static final String PATTERN_NOME = "[a-zA-ZçÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ ]+";
    public static final String PATTERN_NATURALIDADE = "[a-zA-ZçÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ\\- ]+";
    public static final String PATTERN_NOME_EMPRESA = "[-a-zA-ZçÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ,.&@_ ]+";
    public static final String PATTERN_NUM_DOC_IDENTIFICACAO = "[0-9A-Za-z/]+";
    public static final String PATTERN_NUIT = "\\d{8}[0-9]";
    public static final String PATTERN_ENDERECO = "[-a-zA-Z0-9çÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ.,\\- ]+";
    public static final String PATTERN_EMAIL = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    public static final String PATTERN_CELULAR = "[8][2,4,6,7]\\d{7}";
    final public static String ERROR_STYLE_LINEAR_TEXT = "LINEAR";
    final public static String ERROR_STYLE_ICON_TEXT = "ICON";
}
