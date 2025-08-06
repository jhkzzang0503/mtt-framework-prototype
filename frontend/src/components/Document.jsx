import React, { useState } from 'react';
import { SimpleTreeView, TreeItem } from '@mui/x-tree-view';
import TextField from '@mui/material/TextField';
import projectSpecData from '@/project_spec.json';
import { v4 as uuidv4 } from 'uuid';

const Document = () => {
  const [projectSpec, setProjectSpec] = useState(projectSpecData);
  const [searchTerm, setSearchTerm] = useState('');
  const [expanded, setExpanded] = useState([]);

  const handleToggle = (event, nodeIds) => {
    setExpanded(nodeIds);
  };

  const renderTree = (data, keyPrefix = '') => {
    if (Array.isArray(data)) {
      return data.map((item, index) => {
        const newKeyPrefix = keyPrefix ? `${keyPrefix}.${index}` : `${index}`;
        const uniqueId = uuidv4();
        const label = item.name || item.feature_name || item.module_name || item.component_name || index;
        return (
            <TreeItem key={uniqueId} itemId={uniqueId} label={label} id={uniqueId}>
              {renderTree(item, newKeyPrefix)}
            </TreeItem>
        );
      });
    } else if (typeof data === 'object' && data !== null) {
      return Object.keys(data).map((key) => {
        const newKeyPrefix = keyPrefix ? `${keyPrefix}.${key}` : key;
        const uniqueId = uuidv4();

        if (searchTerm && !newKeyPrefix.toLowerCase().includes(searchTerm.toLowerCase())) {
          return null;
        }

        const isExpanded = expanded.includes(uniqueId);

        return (
            <TreeItem key={uniqueId} itemId={uniqueId} label={key} id={uniqueId} expanded={isExpanded}>
              {typeof data[key] === 'object' && data[key] !== null ? renderTree(data[key], newKeyPrefix) : <span>{JSON.stringify(data[key])}</span>}
            </TreeItem>
        );
      });
    }

    return <span>{JSON.stringify(data)}</span>;
  };

  return (
      <div>
        <TextField
            label="Search"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            variant="outlined"
            margin="normal"
        />
        <SimpleTreeView
            defaultCollapseIcon={null}
            defaultExpandIcon={null}
            expanded={expanded}
            onNodeToggle={handleToggle}
        >
          {renderTree(projectSpec)}
        </SimpleTreeView>
      </div>
  );
};

export default Document;