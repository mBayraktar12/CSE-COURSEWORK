import torch
from torch_geometric.data import InMemoryDataset
from torch_geometric.data import Data
from utils import sample_mask

class Twibot20(InMemoryDataset):
    def __init__(self, root, transform=None, pre_transform=None):
        super().__init__(root, transform, pre_transform)
        self.data, self.slices = torch.load(self.processed_paths[0])
        self.root = root

    @property
    def raw_file_names(self):
        return ['some_file_1', 'some_file_2', ...]


    @property
    def processed_file_names(self):
        return ['data.pt']



    def process(self):
        labels = torch.load(self.root + "/label.pt", map_location=torch.device('cpu'))
        des_tensor = torch.load(self.root + "/des_tensor.pt", map_location=torch.device('cpu'))
        tweets_tensor1 = torch.load(self.root + "/tweets_tensor_p1.pt",map_location=torch.device('cpu'))
        tweets_tensor2 = torch.load(self.root + "/tweets_tensor_p2.pt",map_location=torch.device('cpu'))
        tweets_tensor = torch.cat([tweets_tensor1, tweets_tensor2], 0)
        num_prop = torch.load(self.root + "/num_prop.pt",map_location=torch.device('cpu'))
        category_prop = torch.load(self.root + "/category_prop.pt",map_location=torch.device('cpu'))
        edge_index = torch.load(self.root + "/edge_index.pt",map_location=torch.device('cpu'))
        edge_type = torch.load(self.root + "/edge_type.pt",map_location=torch.device('cpu'))
        x = torch.cat([des_tensor, tweets_tensor, num_prop, category_prop], 1)

        # The Data class from the torch_geometric.data module is a fundamental class in PyTorch Geometric (PyG) used to represent 
        # a single graph or dataset. 
        # It provides a convenient way to store and manipulate graph-structured data.

        # The InMemoryDataset class is a base class provided by PyTorch Geometric (PyG) that extends the torch_geometric.data.Dataset class. 
        # It is used as a base class for creating custom graph datasets that can be loaded entirely into memory.

        # The loaded data is then concatenated using torch.cat to create the x tensor, which contains the features for each data point.

        # Next, the method filters out any edges in the edge_index that exceed a certain threshold value (11826 in this case). 
        # This is done by creating a mask (m0 and m1) for filtering out the invalid edges and combining them to create 
        # a final mask m. The x tensor is also sliced to match the filtered number of data points.


        m0 = edge_index[0, :] > 11826
        m1 = edge_index[1, :] > 11826
        m = m0 + m1
        x = x[:11826, :]

        data = Data(x=x, y=labels, edge_index=edge_index)
        data.edge_index = edge_index[:, ~m]
        data.edge_type = edge_type[~m]
        sample_number = len(data.x)

        train_idx = range(8278)
        val_idx = range(8278, 8278 + 2365)
        test_idx = range(8278 + 2365, 8278 + 2365 + 1183)

        data.train_mask = sample_mask(train_idx, sample_number)
        data.val_mask = sample_mask(val_idx, sample_number)
        data.test_mask = sample_mask(test_idx, sample_number)

        data_list = [data]

        if self.pre_filter is not None:
            data_list = [data for data in data_list if self.pre_filter(data)]

        if self.pre_transform is not None:
            data_list = [self.pre_transform(data) for data in data_list]

        data, slices = self.collate(data_list)
        torch.save((data, slices), self.processed_paths[0])
