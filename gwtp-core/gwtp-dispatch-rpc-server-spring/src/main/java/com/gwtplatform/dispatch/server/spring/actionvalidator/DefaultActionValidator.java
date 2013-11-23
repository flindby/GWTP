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

package com.gwtplatform.dispatch.server.spring.actionvalidator;

import com.gwtplatform.dispatch.server.actionvalidator.AbstractDefaultActionValidator;

/**
 * The default {@link com.gwtplatform.dispatch.server.actionvalidator.ActionValidator} spring implementation.
 * It's here just to define default action validator as a spring bean.
 *
 * @author Peter Simun
 *
 * @deprecated Please use {@link com.gwtplatform.dispatch.rpc.server.spring.actionvalidator.DefaultActionValidator}.
 */
@Deprecated
public class DefaultActionValidator extends AbstractDefaultActionValidator {
}
