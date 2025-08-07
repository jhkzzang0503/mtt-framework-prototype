
import React, { useState } from 'react';
import { DndContext, rectIntersection, pointerWithin, MouseSensor, TouchSensor, useSensor, useSensors, getFirstCollision } from '@dnd-kit/core';
import useBuilderStore from '@/store';
import ModulesLayout from '@/components/ModulesLayout';
import BuilderCanvas from '@/components/BuilderCanvas';
import ModulesSet from '@/components/modulesSet';

const BuilderPage = () => {
    const { addItem, moveItem, items, updateItemStyle, selectItem, selectedItemId, deleteItem } = useBuilderStore();
    const [selectedModuleInfo, setSelectedModuleInfo] = useState(null);

    const sensors = useSensors(
        useSensor(MouseSensor, {
            activationConstraint: {
                distance: 10,
            },
        }),
        useSensor(TouchSensor, {
            activationConstraint: {
                delay: 250,
                tolerance: 5,
            },
        })
    );

    const handleDragEnd = (event) => {
        const { active, over } = event;
        console.log("Drag Ended. Active:", active.id, "Over:", over?.id);

        if (!over) {
            return;
        }

        const isSidebarModule = active.data.current?.isSidebarModule;

        if (isSidebarModule) {
            if (over.id === 'canvas' || over.id === 'top-drop-zone') {
                addItem({
                    type: active.data.current.type,
                    renderedComponent: active.data.current.renderComponent,
                });
            } else {
                const overIndex = items.findIndex(item => item.id === over.id);
                if (overIndex !== -1) {
                    addItem({
                        type: active.data.current.type,
                        renderedComponent: active.data.current.renderComponent,
                    }, overIndex + 1);
                }
            }
        }
        else if (active.id !== over.id) {
            const oldIndex = items.findIndex(item => item.id === active.id);
            const newIndex = items.findIndex(item => item.id === over.id);

            if (oldIndex !== -1 && newIndex !== -1) {
                moveItem(oldIndex, newIndex);
            }
        }
    };

    const handleStyleChange = (styles) => {
        if (selectedItemId) {
            updateItemStyle(selectedItemId, styles);
            const updatedItem = useBuilderStore.getState().items.find(item => item.id === selectedItemId);
            setSelectedModuleInfo(updatedItem);
        }
    };

    const handleClickItem = (e, id) => {
        e.stopPropagation();
        selectItem(id);
        const selectedItem = useBuilderStore.getState().items.find(item => item.id === id);
        setSelectedModuleInfo(selectedItem);
    };

    const collisionDetectionStrategy = (args) => {
        const pointerCollisions = pointerWithin(args);

        if (pointerCollisions.length > 0) {
            return pointerCollisions;
        }
        return rectIntersection(args);
    };

    return (
        <DndContext sensors={sensors} collisionDetection={collisionDetectionStrategy} onDragEnd={handleDragEnd}>
            <div className="row h-100 flex-grow-1 m-0">
                <div className="col-md-2 px-0" style={{ overflowY: 'auto' }}>
                    <ModulesLayout />
                </div>
                <div className="col-md-8" style={{ overflowY: 'auto' }}>
                    <BuilderCanvas
                        items={items}
                        selectedItemId={selectedItemId}
                        onItemClick={handleClickItem}
                        selectItem={selectItem}
                        deleteItem={deleteItem}
                    />
                </div>
                <div className="col-md-2 bg-light border-start p-3" style={{ overflowY: 'auto' }}>
                    <ModulesSet selectedModule={selectedModuleInfo} onStyleChange={handleStyleChange} />
                </div>
            </div>
        </DndContext>
    );
};

export default BuilderPage;