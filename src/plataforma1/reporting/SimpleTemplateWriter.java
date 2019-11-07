package plataforma1.reporting;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

/**
 * <p>Utilidad genérica para generar archivos html a partir de plantillas Freemarker</p>
 */
public class SimpleTemplateWriter {

    private static final Logger logger = Logger.getLogger(ReporteTabularPdfWriter.class.getName());

    /**
     * Genera html utilizando como salida outputStream.
     *
     * @param data                     POJO que se utiliza como base de navegación de grafo en la plantilla, referenciado en la misma como "data"
     * @param classForTempleateLoading clase a partir de cuyo classloader se cargan los archivos de plantilla
     * @param templateSpec             especificación del archivo de plantilla
     * @param outputStream             salida del archivo HTML
     * @throws Exception
     */
    public void writeDocument(Object data, Class classForTempleateLoading, String templateSpec, OutputStream outputStream) throws Exception {
        Template template = createConfiguration(classForTempleateLoading).getTemplate(templateSpec);
        SimpleHash rootModel = new SimpleHash();
        rootModel.put("data", data);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        template.process(rootModel, writer, ObjectWrapper.BEANS_WRAPPER);
        writer.flush();
    }

    private Configuration createConfiguration(Class classForTempleateLoading) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(classForTempleateLoading, "");
        //configuration.setDirectoryForTemplateLoading(new File("./"));
        configuration.setObjectWrapper(ObjectWrapper.DEFAULT_WRAPPER);
        return configuration;
    }
}
