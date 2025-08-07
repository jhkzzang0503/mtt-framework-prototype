import React from 'react';

function Footer({ style, className }) {
    return (
        <footer style={style} className={`footer mt-auto py-3 bg-light ${className || ''}`}>{/* 클래스 이름 변경 */}
            <div className="container">
                <span>© 2025 mtt-framework-prototype. All rights reserved.</span>
            </div>
        </footer>
    );
}

export default Footer;
