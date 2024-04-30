import { Routes, Route, Outlet, Link } from "react-router-dom";
import Layout from "./components/Layout";
import Profile from "./components/Profile";
import Resume from "./components/Resume";
import Skill from "./components/Skill";
import Experience from "./components/Experience";
import SkillEdit from "./components/SkillEdit";
import ExperienceEdit from "./components/ExperienceEdit";
import Professional from "./components/Professional";
import ProfessionalEdit from "./components/ProfessionalEdit";
import Education from "./components/Education";
import EducationEdit from "./components/EducationEdit";
import Search from "./components/Search";
import AgentResume from "./components/AgentResume";


function App() {

  return (
    <>
         <Layout />
         <Routes>
            <Route path="profile.do" element={<Profile />} />
            <Route path="education.do" element={<Education/>}/>
            <Route path="education.do/:id" element={<EducationEdit/>}/>
            <Route path="skills.do" element={<Skill />} />
            <Route path="skills.do/:id" element={<SkillEdit />} />
            <Route path="experience.do" element={<Experience />} />
            <Route path="experience.do/:id" element={<ExperienceEdit />} />
            <Route path="professional.do" element={<Professional />} />
            <Route path="professional.do/:id" element={<ProfessionalEdit />} />
            <Route path="resume.do" element={<Resume />} />
            <Route path="search.do" element={<Search />} />
            <Route path="resumes.do/:id" element={<AgentResume/>}/>
         </Routes>
    </>
  )
}

export default App;
