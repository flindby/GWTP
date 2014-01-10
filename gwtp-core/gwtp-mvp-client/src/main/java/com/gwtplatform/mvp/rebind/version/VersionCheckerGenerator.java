/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.mvp.rebind.version;

import java.io.PrintWriter;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.version.VersionChecker;

/**
 * blablah.
 */
public class VersionCheckerGenerator extends Generator {
    private static final String IMPL_PACKAGE_NAME = VersionChecker.class.getPackage().getName();
    private static final String IMPL_TYPE_NAME = VersionChecker.class.getSimpleName() + "Impl";

    @Override
    public String generate(TreeLogger logger, GeneratorContext context,
                           String typeName) throws UnableToCompleteException {
        logger.log(TreeLogger.Type.INFO, "==== Started VersionCheckerGenerator LOL");

        ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(IMPL_PACKAGE_NAME,
                IMPL_TYPE_NAME);

        composerFactory.addImplementedInterface(VersionChecker.class.getName());

        PrintWriter printWriter = context.tryCreate(logger, IMPL_PACKAGE_NAME, IMPL_TYPE_NAME);

        if (printWriter != null) {
            logger.log(TreeLogger.Type.INFO, "==== context: " + context.toString());
            logger.log(TreeLogger.Type.INFO, "==== printwriter: " + printWriter.toString());
            SourceWriter sourceWriter = composerFactory.createSourceWriter(context, printWriter);

            logger.log(TreeLogger.Type.INFO, "Your GWTP version: " + getYourGwtpVersion());
            logger.log(TreeLogger.Type.INFO, "Latest GWTP version: " + getLatestGwtpVersion());

            sourceWriter.commit(logger);
        }

        return IMPL_PACKAGE_NAME + "." + IMPL_TYPE_NAME;
    }

    private String getLatestGwtpVersion() {
        return new GwtpVersionExtractor().getLatestVersion();
    }

    private String getYourGwtpVersion() {
        return Presenter.class.getPackage().getImplementationVersion();
    }
}
