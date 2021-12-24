// const components = require('prismjs/components');
// const allLanguages = Object.keys(components.languages).filter((item) => item !== 'meta');

module.exports = {
    presets: [
        '@vue/cli-plugin-babel/preset'
    ],
    plugins: [
        [
            'prismjs',
            {
                // languages: ['json'],
                languages: ['Java', 'javascript', 'C++', 'python', 'css', 'json'],
                "plugins": ["line-numbers"],
                "css": true
            },
        ],
    ],
}
