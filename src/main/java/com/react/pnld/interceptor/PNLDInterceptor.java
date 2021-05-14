package com.react.pnld.interceptor;

import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.RowProcessorErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PNLDInterceptor implements RowProcessorErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(PNLDInterceptor.class);

    @Override
    public void handleError(DataProcessingException e, Object[] objects, ParsingContext parsingContext) {
        logger.info("handlerError parsingContext={}", parsingContext.currentParsedContent());
        logger.error(e.getMessage(), e);
    }
}
