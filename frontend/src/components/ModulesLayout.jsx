// src/components/ModulesLayout.jsx
import React, { Suspense } from 'react';
import getModules from '@/utils/getModules.jsx';
import DraggableModule from '@/components/DraggableModule';

const ModulesLayout = () => {
    const modules = getModules();

    return (
        <div className="sidebar-panel h-100 p-3 bg-light border-end d-flex flex-column"
             style={{overflowY: 'auto'}}>
            {Object.entries(modules).map(([categoryName, categoryModules]) => (
                <div key={categoryName}>
                    <h3 className="sidebar-category-title">{categoryName}</h3>
                    <div className="module-grid">
                        {categoryModules.map((module) => {
                            // 여기에서 React.lazy 컴포넌트를 생성하여 렌더링
                            const LazyComponent = React.lazy(module.componentImportFunc);
                            return (
                                <DraggableModule
                                    key={module.id}
                                    id={`sidebar-draggable-${module.id}`}
                                    type={module.name}
                                    path={module.path}
                                    renderComponent={module.componentImportFunc} // import 함수를 전달
                                >
                                    <div className="module-preview-card">
                                        <div className="preview-content-wrapper">
                                            <div className="preview-content">
                                                <Suspense fallback={<div>Loading...</div>}>
                                                    <LazyComponent />
                                                </Suspense>
                                            </div>
                                        </div>
                                        <div className="preview-title">{module.name}</div>
                                    </div>
                                </DraggableModule>
                            );
                        })}
                    </div>
                </div>
            ))}
        </div>
    );
};

export default ModulesLayout;