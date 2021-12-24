import axios from "axios"
import {ElMessage} from "element-plus"
import router from "@/router"

// 请求拦截器
axios.interceptors.request.use(config => {
    let item = window.sessionStorage.getItem('tokenStr')
    if (item) {
        config.headers['Authorization'] = item
    }
    return config
}, error => {
    console.log(error)
})


// 响应拦截器
axios.interceptors.response.use(success => {
    if (success.status && success.status === 200) {
        if (success.data.code === 500 ||
            success.data.code === 401 ||
            success.data.code === 403) {
            ElMessage.error({message: success.data.message})
            return
        }
        if (success.data.message) {
            ElMessage.success({message: success.data.message})
        }
        return success.data;
    }
}, error => {
    if (error.response.code === 504 ||
        error.response.code === 404) {
        ElMessage.error({message: '服务器繁忙，请稍后访问...'})
    } else if (error.response.code === 404) {
        ElMessage.error({message: '权限不足，请联系管理员...'})
    } else if (error.response.code === 401) {
        ElMessage.error({message: '尚未登陆，请登录...'})
        router.replace('/')
    } else {
        if (error.response.data.message) {
            ElMessage.error({message: error.response.data.message})
        } else {
            ElMessage.error({message: '未知错误'})
        }
    }
})

let base = ''
let headers = {'Access-Control-Allow-Origin': '*'}

export const postRequest = (url, data) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        headers,
        data
    })
}

export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        headers,
        params
    })
}
