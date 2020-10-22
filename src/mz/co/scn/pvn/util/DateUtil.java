package mz.co.scn.pvn.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtil {

    private static List<SimpleDateFormat> suportedFormats = new ArrayList<SimpleDateFormat>();

    /**
     * Este método cria uma data com usando a data passada por parametro e para a mesma data atribui a hora passada por
     * parametro;
     *
     * @param date
     * @param hora
     * @return Date
     * @throws Exception
     */
    public static Date createDate(Date date, String hora) throws Exception {
        Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
        Matcher matcher;
        matcher = pattern.matcher(hora);

        if (!matcher.matches()) {
            throw new Exception("Invalid time format! HH:mm");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String[] horaS = hora.split(":");

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = Integer.parseInt(horaS[0]);
        int minute = Integer.parseInt(horaS[1]);

        Calendar calendarFinal = new GregorianCalendar(year, month, day, hour, minute);

        return calendarFinal.getTime();
    }

    /**
     * Convert String with various formats into java.util.Date
     *
     * @param input Date as a string
     * @return java.util.Date object if input string is parsed successfully else returns null
     */
    public static Date convertToDate(String input) {
        suportedFormats.add(new SimpleDateFormat("M/dd/yyyy"));
        suportedFormats.add(new SimpleDateFormat("dd/mm/yyyy"));
        suportedFormats.add(new SimpleDateFormat("dd-mm-yyyy"));
        suportedFormats.add(new SimpleDateFormat("dd.M.yyyy"));
        suportedFormats.add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
        suportedFormats.add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
        suportedFormats.add(new SimpleDateFormat("dd.MMM.yyyy"));
        suportedFormats.add(new SimpleDateFormat("dd-MMM-yyyy"));

        Date date = null;
        if (input == null) {
            return null;
        }
        for (SimpleDateFormat format : suportedFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }

    /**
     * Este metodo e devolve a data em formato de string dd/MM/yyyy
     *
     * @param date
     * @return String
     */
    public static String parse(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String dateR = simpleDateFormat.format(date);

        return dateR;
    }

    /**
     * Este metodo e devolve a data em formato de string com base do padrao entregue.
     *
     * @param date
     * @param pattern
     * @return String
     */
    public static String parse(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String dateR = simpleDateFormat.format(date);

        return dateR;
    }

    /**
     * Este metodo e devolve a data em formato de string com base do padrao entregue.
     *
     * @param date
     * @param pattern
     * @return String
     */
    public static String parse(Date date, String pattern, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);

        String dateR = simpleDateFormat.format(date);

        return dateR;
    }

    /**
     * Este metodo e para a criacao de constraints e devolve uma string no formato yyyyMMdd
     *
     * @param date
     * @return String
     */
    public static String parse1(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String dateR = simpleDateFormat.format(date);

        return dateR;
    }

    /**
     * Este metodo e para a consultas na BD e devolve uma string no formato yyyy-MM-dd
     *
     * @param date
     * @return String
     */
    public static String parse3(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateR = simpleDateFormat.format(date);

        return dateR;
    }

    /**
     * Este metodo e para a criacao de constraints e devolve uma string no formato HHmm
     *
     * @param time
     * @return String
     */
    public static String parse2(Time time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");

        String dateR = simpleDateFormat.format(time);

        return dateR;
    }

    /**
     * @param string
     * @return Date
     * @throws ParseException
     */
    public static Date parse1(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;

        try {
            date = simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param string
     * @return Date
     * @throws ParseException
     */
    public static Date parse(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @return Date
     */
    public static Date currentDate() {
        Calendar date = Calendar.getInstance();

        return date.getTime();
    }

    /**
     * @return int - o ano corrente.
     */
    public static int currentYear() {
        Calendar date = Calendar.getInstance();

        return date.get(Calendar.YEAR);
    }

    /**
     * @return Date
     */
    @SuppressWarnings("deprecation")
    public static Time currentTime() {
        Time time = new Time(DateUtil.currentDate().getHours(), DateUtil.currentDate().getMinutes(),
                DateUtil.currentDate().getSeconds());

        return time;
    }

    /**
     * Este metodo recebe uma data e um numero de dias e devolve da data mais os numero de dias adicionado
     *
     * @param data
     * @param dias
     * @return {@link Date}
     */
    public static Date addDays(Date data, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        cal.add(Calendar.DAY_OF_MONTH, dias);

        return cal.getTime();
    }

    /**
     * Este metodo recebe uma data e um numero de dias e devolve da data mais os numero de anos adicionado.
     *
     * @param data
     * @param anos
     * @return {@link Date}
     */
    public static Date addYears(Date data, int anos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        cal.add(Calendar.YEAR, anos);

        return cal.getTime();
    }

    /**
     * Este metodo recebe uma hora e uns minutos e devolve a hora mais os numero de minutos adicionados
     *
     * @param time
     * @param minutos
     * @return {@link Date}
     */
    public static Time addMins(Time time, int minutos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);

        cal.add(Calendar.MINUTE, minutos);

        return new Time(cal.getTime().getTime());
    }

    /**
     * Este metodo recebe uma data e uns minutos e devolve a data com os minutos adicionados
     *
     * @param date
     * @param minutos
     * @return {@link Date}
     */
    public static Date addMins(Date date, int minutos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MINUTE, minutos);

        return cal.getTime();
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static double diferencaEmDias(Date dataInicial, Date dataFinal) {
        double result = 0;

        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24;
        long horasRestantes = (diferenca / 1000) / 60 / 60 % 24;
        result = (diferencaEmDias + (horasRestantes / 24d));

        return result;
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static String diferencaEmHorasMinutos(Date dataInicial, Date dataFinal) {
        String diff = "";
        long timeDiff = Math.abs(dataInicial.getTime() - dataFinal.getTime());

        String hour = "" + TimeUnit.MILLISECONDS.toHours(timeDiff);
        String minute = "" + (TimeUnit.MILLISECONDS.toMinutes(timeDiff)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));

        if (hour.length() == 1)
            hour = "0".concat(hour);

        if (minute.length() == 1)
            minute = "0".concat(minute);

        diff = hour.concat(":").concat(minute);

        return diff;
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static long diferencaEmHoras(Date dataInicial, Date dataFinal) {
        long diff = 0;
        long timeDiff = Math.abs(dataInicial.getTime() - dataFinal.getTime());
        diff = TimeUnit.MILLISECONDS.toHours(timeDiff);
        return diff;
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static long diferencaEmMinutos(Date dataInicial, Date dataFinal) {
        long minutos = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        minutos = (long) Math.floor((diferenca % 3600000) / 60000);

        return minutos;
    }

    /**
     * @param date
     * @return
     */
    public static Long getTimeStamp(Date date) {
        Timestamp timeStampDate = new Timestamp(date.getTime());
        return timeStampDate.getTime();
    }

    /**
     * Este método recebe uma data e retorna o dia.
     *
     * @param date
     * @return int
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Este método recebe uma data e retorna o m�s.
     *
     * @param date
     * @return int - O m�s
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH);
    }

    /**
     * Este método recebe uma data e retorna o ano.
     *
     * @param date
     * @return int - O m�s
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * Este método serve para
     *
     * @param startDate
     * @param endDate
     */
    public static String printFormatDifference(Date startDate, Date endDate) {
        // milliseconds
        long different = 0;
        if (endDate != null && startDate != null)
            different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return String.format("%d dias, %d horas, %d minutos, %d segundos%n", elapsedDays, elapsedHours, elapsedMinutes,
                elapsedSeconds);
    }

    /**
     * Este método serve para calcular a idade de uma pessoa.
     *
     * @return int - A idade calculada.
     */
    public static int calcIdade(Date dataNasc) {
        Calendar dateOfBirth = new GregorianCalendar();

        dateOfBirth.setTime(dataNasc);

        // Cria um objecto calendar com a data atual.
        Calendar today = Calendar.getInstance();

        // Obtem a idade baseado no ano.
        int idade = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        // Se a data de hoje e antes da data de Nascimento, entao diminui 1.
        if (today.before(dateOfBirth)) {
            idade--;
        }

        return idade;
    }
}
