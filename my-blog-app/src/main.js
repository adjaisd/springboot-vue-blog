import {createApp} from 'vue'
import App from './App.vue'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'

import router from "@/router"
import store from "@/store"

import {getRequest, postRequest} from "@/api/api";

let elementApp  = createApp(App)
elementApp.config.globalProperties.$postRequest = postRequest
elementApp.config.globalProperties.$getRequest = getRequest

// 自定义时间转换函数 Long -> Date
import {formatTime} from '@/utils/time'
elementApp.config.globalProperties.$formatTime = formatTime

elementApp.use(ElementPlus).use(router).use(store)


// 代码高亮
import VueMarkdownEditor,{ xss } from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import hljs from 'highlight.js'   // highlightjs

VueMarkdownEditor.use(githubTheme, {
  Hljs: hljs,
})

// 渲染HTML
import VMdPreviewHtml from '@kangc/v-md-editor/lib/preview-html'
import '@kangc/v-md-editor/lib/style/preview-html.css'
elementApp.use(VMdPreviewHtml)

// 渲染markdown
import VmdPreview from '@kangc/v-md-editor/lib/preview'
import '@kangc/v-md-editor/lib/style/preview.css'
VmdPreview.use(githubTheme, {Hljs: hljs})
elementApp.use(VmdPreview)

// markdown转化为html
// const html = xss.process(VueMarkdownEditor.vMdParser.themeConfig.markdownParser.render('### 标题'));
const mdToHtml = (md)=>xss.process(VueMarkdownEditor.vMdParser.themeConfig.markdownParser.render(md))


// 行号
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
VueMarkdownEditor.use(createLineNumbertPlugin())


elementApp.config.globalProperties.$mdToHtml = mdToHtml

// 复制代码
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css'
import createCopyCodePlugin from "@kangc/v-md-editor/es/plugins/copy-code"
VueMarkdownEditor.use(createCopyCodePlugin())


// 内容定位
import createAlignPlugin from '@kangc/v-md-editor/lib/plugins/align';
VueMarkdownEditor.use(createAlignPlugin());


// 表情包
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
VueMarkdownEditor.use(createEmojiPlugin());


// katex
// import createKatexPlugin from '@kangc/v-md-editor/lib/plugins/katex/cdn';
// VueMarkdownEditor.use(createKatexPlugin());



elementApp.use(VueMarkdownEditor)
elementApp .mount('#app')
