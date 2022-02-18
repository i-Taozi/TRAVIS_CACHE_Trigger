/*
 * Solo - A small and beautiful blogging system written in Java.
 * Copyright (c) 2010-present, b3log.org
 *
 * Solo is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *         http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */
package org.b3log.solo.processor;

import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.b3log.latke.Keys;
import org.b3log.latke.http.Request;
import org.b3log.latke.http.RequestContext;
import org.b3log.latke.http.renderer.AbstractFreeMarkerRenderer;
import org.b3log.solo.util.Skins;
import org.b3log.solo.util.Statics;

import java.io.StringWriter;
import java.util.Map;

/**
 * Skin renderer.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.1.0, Jun 10, 2020
 * @since 2.9.1
 */
public final class SkinRenderer extends AbstractFreeMarkerRenderer {

    /**
     * HTTP request context.
     */
    private final RequestContext context;

    public static final String LATKE_INFO = "\n<!-- Generated by Latke (https://github.com/88250/latke) in %1$dms, %2$s -->";

    /**
     * Constructs a skin renderer with the specified request context and template name.
     *
     * @param context      the specified request context
     * @param templateName the specified template name
     */
    public SkinRenderer(final RequestContext context, final String templateName) {
        this.context = context;
        this.context.setRenderer(this);
        setTemplateName(templateName);
    }

    @Override
    protected Template getTemplate() {
        final String templateName = getTemplateName();
        Template ret = Skins.getSkinTemplate(context, templateName);
        if (null == ret) {
            // 优先使用皮肤内的登录、报错等模板 https://github.com/b3log/solo/issues/12566
            ret = Skins.getTemplate(templateName);
        }
        return ret;
    }

    /**
     * Processes the specified FreeMarker template with the specified request, data model, pjax hacking.
     *
     * @param request   the specified request
     * @param dataModel the specified data model
     * @param template  the specified FreeMarker template
     * @return generated HTML
     * @throws Exception exception
     */
    @Override
    protected String genHTML(final Request request, final Map<String, Object> dataModel, final Template template) throws Exception {
        final boolean isPJAX = isPJAX(context);
        dataModel.put("pjax", isPJAX);

        if (!isPJAX) {
            return super.genHTML(request, dataModel, template);
        }

        final StringWriter stringWriter = new StringWriter();
        template.setOutputEncoding("UTF-8");
        template.process(dataModel, stringWriter);
        final long endTimeMillis = System.currentTimeMillis();
        final String dateString = DateFormatUtils.format(endTimeMillis, "yyyy/MM/dd HH:mm:ss");
        final long startTimeMillis = (Long) context.attr(Keys.HttpRequest.START_TIME_MILLIS);
        final String latke = String.format("\n<!-- Generated by Latke (https://github.com/88250/latke) in %1$dms, %2$s -->", endTimeMillis - startTimeMillis, dateString);
        final String pjaxContainer = context.header("X-PJAX-Container");

        final String html = stringWriter.toString();
        final String[] containers = StringUtils.substringsBetween(html,
                "<!---- pjax {" + pjaxContainer + "} start ---->",
                "<!---- pjax {" + pjaxContainer + "} end ---->");
        if (null == containers) {
            return html + latke;
        }

        return String.join("", containers) + latke;
    }

    @Override
    protected void beforeRender(final RequestContext context) {
    }

    @Override
    protected void afterRender(final RequestContext context) {
        Statics.put(context);
    }

    /**
     * Determines whether the specified request is sending with pjax.
     *
     * @param context the specified request context
     * @return {@code true} if it is sending with pjax, otherwise returns {@code false}
     */
    public static boolean isPJAX(final RequestContext context) {
        final boolean pjax = Boolean.valueOf(context.header("X-PJAX"));
        final String pjaxContainer = context.header("X-PJAX-Container");
        return pjax && StringUtils.isNotBlank(pjaxContainer);
    }
}