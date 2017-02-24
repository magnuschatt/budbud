package chatt.budbud

import org.springframework.web.servlet.ModelAndView

fun modelView(viewName: String, block: ModelAndView.() -> Unit = {}): ModelAndView {
    return ModelAndView(viewName).apply(block)
}

fun modelView(block: ModelAndView.() -> Unit = {}): ModelAndView {
    return ModelAndView().apply(block)
}