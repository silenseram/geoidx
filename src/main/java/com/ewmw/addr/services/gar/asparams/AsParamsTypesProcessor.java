package com.ewmw.addr.services.gar.asparams;

import com.ewmw.addr.interfaces.Processor;
import com.ewmw.addr.services.gar.xml.parser.XmlFileParserThread;
import com.ewmw.addr.services.gar.files.GarFilesManager;
import com.ewmw.addr.services.gar.params.HouseParamsTypesService;
import com.ewmw.addr.services.gar.xml.parser.XmlParserThreadsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AsParamsTypesProcessor implements Processor {
    @Autowired
    HouseParamsTypesService houseParamsTypesService;

    @Autowired
    GarFilesManager garFilesManager;

    @Autowired
    protected ApplicationContext springContext;

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    XmlParserThreadsManager xmlParserThreadsManager;


    @Override
    public void process() {
        xmlParserThreadsManager = new XmlParserThreadsManager();

        List<File> asHouseParamsFiles = garFilesManager.getAsHouseParamsFiles();

        asHouseParamsFiles
                .forEach(file -> {
                    ParamsXmlProcessor anotherInstance = new ParamsXmlProcessor();
                    autowireCapableBeanFactory.autowireBean(anotherInstance);

                    xmlParserThreadsManager.run(new XmlFileParserThread(
                            file.getAbsolutePath(),
                            "PARAM",
                            anotherInstance
                    ));
                });

    }

}
