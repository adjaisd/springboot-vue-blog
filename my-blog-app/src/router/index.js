import {createRouter, createWebHashHistory} from "vue-router";
import Login from "@/components/Login"
import Home from "@/Home";
import MessageBoard from "@/components/main/MessageBoard";
import Index from "@/components/main/Index"
import Tag from "@/components/main/Tag";
import Log from "@/components/main/Log";
import Register from "@/components/Register";
import Write from "@/components/main/common/ElMain/article/Write";
import Read from "@/components/main/common/ElMain/article/Read";
import MarkdownEditor from "@/components/main/common/ElMain/article/MarkdownEditor";

const routes = [
    {
        path: '/',
        redirect: '/index',
        component: Home,
    },
    {
        path: '/login',
        component: Login,
    },
    {
        path: '/register',
        component: Register
    },
    {
        path: '/home',
        component: Home,
        children: [
            {path: '/index', component: Index},
            {path: '/tag', component: Tag},
            {path: '/log', component: Log},
            {path: '/messageBoard', component: MessageBoard},
        ]
    },
    {path: '/article/write', component: Write},
    {path: '/article/read', component: Read},
    {path: '/article/editor', component: MarkdownEditor},
    {path: '/article/view/:id', component: Read, props: true},
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
