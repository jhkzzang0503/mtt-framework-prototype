import React, { Suspense } from 'react';
import getModules from '@/utils/getModules';
import DraggableModule from '@/components/DraggableModule';
import useBuilderStore from "@/store";

const ModulesLayout = () => {
    const modules = getModules();
    const { items } = useBuilderStore();

    const isAddedToCanvas = (id) => {
        return items.some(item => {
            return item.id === id;
        });
    };

    return (
        <div className="sidebar-panel h-100 p-3 bg-light border-end d-flex flex-column"
             style={{overflowY: 'auto'}}>
            {Object.entries(modules).map(([categoryName, categoryModules]) => (
                <div key={categoryName}>
                    <h3 className="sidebar-category-title">{categoryName}</h3>
                    <div className="module-grid">
                        {categoryModules.map((module) => {
                            console.log("isAddedToCanvas :: " + isAddedToCanvas(module.name))
                            const LazyComponent = React.lazy(module.componentImportFunc);
                            return (
                                <DraggableModule
                                    key={module.id}
                                    id={`sidebar-draggable-${module.id}`}
                                    type={module.name}
                                    path={module.path}
                                    disabled={!isAddedToCanvas(module.id)}
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